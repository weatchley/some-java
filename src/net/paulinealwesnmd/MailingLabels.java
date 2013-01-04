package net.paulinealwesnmd;

import com.cete.dynamicpdf.*;
import com.cete.dynamicpdf.pageelements.TextArea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.paulinealwesnmd.util.db.*;
import com.goldenop.util.db.*;
import com.goldenop.util.misc.*;
import net.paulinealwesnmd.model.*;


public class MailingLabels  extends HttpServlet {
    // Set page dimensions
    int topMargin;
    int bottomMargin;
    float rightMargin;
    float leftMargin;
    // Set the number of labels per page
    int maximumColumns;
    int maximumRows;
    // Set the spacing between the labels
    int horizontalSpace;
    int verticalSpace;
    // These margins are on the labels themsleves
    int labelTopBottomMargin;
    int labelLeftRightMargin;

    Document document;
    Page page;
    float currentColumn, currentRow, labelWidth, labelHeight;
    String labelLine1;
    String labelLine2;
    String labelLine3;
    String labelLine4;
    ServletOutputStream sOut;
    Connection connection;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        topMargin = 36;
        bottomMargin = 36;
        rightMargin = 13.5f;
        leftMargin = 13.5f;
        maximumColumns = 3;
        maximumRows = 10;
        horizontalSpace = 9;
        verticalSpace = 0;
        labelTopBottomMargin = 5;
        labelLeftRightMargin = 15;
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException,ServletException {
        DbConn myConn = null;
        myConn = new DbConn("pka");
        String csiSchema = myConn.getSchemaPath();
        Clients[] cls = null;
        //CeTeConnection ceTe = (CeTeConnection)getServletContext().getAttribute("cetecon");
        //connection = ceTe.getConnection();
        sOut = res.getOutputStream();

        // Create a document and set it's properties
        document = new Document();
        page = new Page(PageSize.LETTER, PageOrientation.PORTRAIT);
        document.setCreator("MailingLabels.java");
        document.setAuthor("PKA");
        document.setTitle("Mailing Labels");

        // Entrypoint for the labels
        currentRow = 1;
        currentColumn = 1;

        ResultSet data = null;
        // Creates a ResultSet for the report
        try {
            cls = Clients.getItemList(myConn);
        } catch (Exception ex1) {
            ex1.printStackTrace(System.err);
        }

        // Loop over the ResultSet and add each label
        try {
			for (int i=0; i<cls.length; i++) {
                labelLine1 = safeDBNull2(cls[i].getFirstName(), "") + " " + safeDBNull2(cls[i].getLastName(), "");
                labelLine2 = safeDBNull2(cls[i].getAddress(), "");
                labelLine3 = safeDBNull2(cls[i].getCity(), "") + " " + safeDBNull2(cls[i].getState(), "") + " " + safeDBNull2(cls[i].getZip(), "");
                labelLine4 = safeDBNull2("", "");
                addLabel();
            }
        } catch (Exception ex2) {
            ex2.printStackTrace(System.err);
        }
        if (page.getElements().size() > 0) {
            document.getPages().add(page);
        }
        // Outputs the MailingLabels to the current web page
        document.drawToWeb(req, res, sOut, "MailingLabels.pdf");
        //ceTe.close();
        myConn.release();
        sOut.close();
    }

    private float findLabelHeight() {
        return (page.getDimensions().getHeight() - (page.getDimensions().getTopMargin()
        + page.getDimensions().getBottomMargin()) - ((maximumRows - 1)
        * verticalSpace)) / maximumRows;
    }

    private float findLabelWidth() {
        return (page.getDimensions().getWidth() - (page.getDimensions().getRightMargin()
        + page.getDimensions().getLeftMargin()) - ((maximumColumns - 1)
        * horizontalSpace)) / maximumColumns;
    }

    private void addLabel() {
        // Add a new page if you are beyond the maximum Rows
        if (currentRow == maximumRows + 1) {
            document.getPages().add(page);
            currentRow = 1;
        }
        // Determines if the the label belongs in the first row or first column of the page
        if (currentColumn > 1 & currentRow > 1) {
            addToPage();
        } else if (currentColumn > 1 & currentRow == 1)	{
            addToFirstRow();
        } else if (currentColumn == 1 & currentRow > 1)	{
            addToFirstColumn();
        } else	{
            page = new Page(PageSize.LETTER, PageOrientation.PORTRAIT);

            page.getDimensions().setTopMargin(topMargin);
            page.getDimensions().setBottomMargin(bottomMargin);
            page.getDimensions().setRightMargin(rightMargin);
            page.getDimensions().setLeftMargin(leftMargin);
            labelWidth = findLabelWidth();
            labelHeight = findLabelHeight();
            addToFirstRowColumn();
        }

        // Incremment your row if you are beyond the maximum columns
        if (currentColumn == maximumColumns + 1) {
            currentRow = currentRow + 1;
            currentColumn = 1;
        }
    }

    // Adds the label on at least row 2 column 2 of the page
    private void addToPage() {
        float x;
        float y;
        y = (currentRow - 1) * (labelHeight + verticalSpace);
        x = (currentColumn - 1) * (labelWidth + horizontalSpace);
        addLabelInfo(x, y);
        currentColumn = currentColumn + 1;
    }

    // Adds the label on the first row of labels
    private void addToFirstRow() {
        float x;
        float y;
        y = 0;
        x = (currentColumn - 1) * (labelWidth + horizontalSpace);
        addLabelInfo(x, y);
        currentColumn = currentColumn + 1;
    }

    // Adds the label to the First column of labels
    private void addToFirstColumn() {
        float x;
        float y;
        y = (currentRow - 1) * (labelHeight + verticalSpace);
        x = 0;
        addLabelInfo(x, y);
        currentColumn = currentColumn + 1;
    }

    // Adds only the first label of every page (row 1 column 1)
    private void addToFirstRowColumn() {
        float x;
        float y;
        y = 0;
        x = 0;
        addLabelInfo(x, y);
        currentColumn = currentColumn + 1;
    }

    // This is where you format the look of each label
    private void addLabelInfo(float x, float y)	{
        TextArea txt1 = new TextArea(labelLine1, x + labelLeftRightMargin,
                y + labelTopBottomMargin, labelWidth
                - (labelLeftRightMargin * 2), 11,
                Font.getTimesRoman(), 11);
        TextArea txt2 = new TextArea(labelLine2, x + labelLeftRightMargin,
                y + labelTopBottomMargin + 12, labelWidth
                - (labelLeftRightMargin * 2), 11,
                Font.getTimesRoman(), 11);
        TextArea txt3 = new TextArea(labelLine3, x + labelLeftRightMargin,
                y + labelTopBottomMargin + 24, labelWidth
                - (labelLeftRightMargin * 2), 11,
                Font.getTimesRoman(), 11);
        TextArea txt4 = new TextArea(labelLine4, x + labelLeftRightMargin,
                y + labelTopBottomMargin + 36, labelWidth
                - (labelLeftRightMargin * 2), 11,
                Font.getTimesRoman(), 11);

        page.getElements().add(txt1);
        page.getElements().add(txt2);
        page.getElements().add(txt3);
        page.getElements().add(txt4);
    }

    private String safeDBNull(String value, ResultSet data) {
        try {
            if (data.wasNull()) {
                return "";
            } else {
                return value;
            }
        } catch (SQLException ex3) {
            ex3.printStackTrace(System.err);
        }
        return "";
    }

    private String safeDBNull2(String value, String def) {
        try {
            if (value != null) {
                return value;
            } else {
                return def;
            }
        } catch (Exception ex3) {
            ex3.printStackTrace(System.err);
        }
        return "";
    }
}