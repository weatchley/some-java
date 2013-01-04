package net.paulinealwesnmd;

import javax.servlet.*;
import javax.servlet.http.*;
// Support classes
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.math.*;
import java.awt.*;
import java.lang.*;
import java.text.*;
import net.paulinealwesnmd.util.db.*;
import com.goldenop.util.db.*;
import com.goldenop.util.misc.*;
import net.paulinealwesnmd.model.*;
import javax.naming.*;


public class doVendor extends HttpServlet {
  // Handle the GET HTTP Method
  public void doGet(HttpServletRequest request,
            HttpServletResponse response)
     throws IOException {

    String message = "";
    try {
        //message = processRequest(request,response);
        generateResponse("Invalid 'GET' request", "n/a","n/a", false, response);
    }
    finally {
    }
  }

  // Handle the POST HTTP Method
  public void doPost(HttpServletRequest request,
             HttpServletResponse response)
     throws IOException {

    String message = "";
    try {
        message = processRequest(request,response);
    }
    finally {
    }
  }


  // Process the request
  private String processRequest(HttpServletRequest request, HttpServletResponse response) {
    String command = request.getParameter("command");
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String address = request.getParameter("address");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    String zip = request.getParameter("zip");
    String phone = request.getParameter("phone");
    String fax = request.getParameter("fax");
    String website = request.getParameter("website");
    boolean isActive = ((request.getParameter("isactive") != null && request.getParameter("isactive").equals("T")) ? true : false);
    String notes = request.getParameter("notes");
    String outLine = "";
    //String nextScript = "home.jsp";
    String nextScript = request.getParameter("nextscript");
    OutputStream toClient;
    HttpSession session = request.getSession();
    boolean success = false;

    command = (command != null && command.compareTo(" ") > 0) ? command : "form";
    nextScript = (nextScript != null && nextScript.compareTo(" ") > 0) ? nextScript : "vendors.jsp";

//    inputstring = (inputstring != null && inputstring.compareTo(" ") > 0) ? inputstring : "";

    DbConn myConn = null;
    try {

        Context initCtx = new InitialContext();
        myConn = new DbConn("pka");
        String csiSchema = myConn.getSchemaPath();
        if (command.equals("add") || command.equals("new")) {
            Vendor item = new Vendor();
            item.setName(name);
            item.setEmail(email);
            item.setAddress(address);
            item.setCity(city);
            item.setState(state);
            item.setZip(zip);
            item.setPhone(phone);
            item.setFax(fax);
            item.setWebsite(website);
            item.setIsActive(isActive);
            item.setNotes(notes);
            item.save(myConn);
            success = true;
            outLine = "";

        } else if (command.equals("update")) {
            Vendor item = new Vendor();
            item.getInfo(Integer.parseInt(id), myConn);
            item.setName(name);
            item.setEmail(email);
            item.setAddress(address);
            item.setCity(city);
            item.setState(state);
            item.setZip(zip);
            item.setPhone(phone);
            item.setFax(fax);
            item.setWebsite(website);
            item.setIsActive(isActive);
            item.setNotes(notes);
            item.save(myConn);
            success = true;
            outLine = "";
        } else if (command.equals("test")) {
            outLine = "test";
        }

    }


    catch (IllegalArgumentException e) {
        outLine = outLine + "IllegalArgumentException caught: " + e.getMessage();
        System.out.println(outLine);
        //ALog.logError(userID, "csi", 0, "Argument error: '" + outLine + "'");
        //log(outLine);
    }


    catch (NullPointerException e) {
        outLine = outLine + "NullPointerException caught: " + e.getMessage();
        System.out.println(outLine);
        //ALog.logError(userID, "csi", 0, "Null Pointer error: '" + outLine + "'");
        //log(outLine);
    }

    //catch (IOException e) {
    //    outLine = outLine + "IOException caught: " + e.getMessage();
//        System.out.println(outLine);
    //    ALog.logActivity(userID, "csi", 0, "Client error: '" + outLine + "'");
    //    //log(outLine);
    //}

    catch (Exception e) {
        outLine = outLine + "Exception caught: " + e.getMessage();
        System.out.println(outLine);
        //ALog.logActivity(userID, "csi", 0, "Misc error: '" + outLine + "'");
        //log(outLine);
    }
    finally {
        try { generateResponse(outLine, command, nextScript, success, response); } catch (Exception i) {}

        myConn.release();
       //log("Test log message\n");
    }


    return outLine;

  }

  // Generate the HTML response
  private void generateResponse(String outLine, String command, String nextScript, boolean success, HttpServletResponse response)
      throws IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <LINK href=\"css/styles.css\" type=text/css rel=STYLESHEET>");
    out.println("<title>Vendor</title>");
    out.println("</head>");
    out.println("<body BGCOLOR=#DEC68B background=images/background.png leftmargin=0 topmargin=0>");
    out.println("<form name=result method=post>");
    out.println("");
    out.println("<input type=hidden name=command value='" + command + "'>");
    out.println("<script language=\"JavaScript\">\n");
    out.println("<!--\n");
    if (success) {
        if (!outLine.equals("")) {
            out.println("alert('" + outLine + "');\n");
        }
        out.println("parent.document.location='" + nextScript + "';\n");
    } else {
        out.println("alert('Error saving client!');\n");
    }
    out.println("//-->\n");
    out.println("</script>\n");
    out.println("");
    out.println("</form>\n</body>\n</html>");

    out.close();
  }
}
