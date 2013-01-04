package net.paulinealwesnmd.model;

// Support classes
import java.io.*;
import java.util.*;
import java.sql.*;
import net.paulinealwesnmd.util.db.*;
import com.goldenop.util.db.*;
import com.goldenop.util.misc.*;
import net.paulinealwesnmd.model.*;

/**
* OfficeCal is the class for calendar in the db
*
* @author   Bill Atchley
*/
public class OfficeCal {
    private int ID = 0;
    private int client = 0;
    private Clients clientObj = null;
    private int refID = 0;
    private boolean isClientApp = false;
    private boolean showedUp = false;
    private java.util.Date eventDate = null;
    private Time begin = null;
    private Time end = null;
    private boolean allDay = false;
    private boolean sendReminder = false;
    private boolean isPublic = false;
    private String description = null;

    private boolean isNew = true;

    public static String SCHEMA = null;
    public static String SCHEMAPATH = null;
    public static String DBTYPE = null;

    /**
    * init new object
    */
    private void init () {
        DbConn tempDB = new DbConn("dummy");
        //SCHEMA = tempDB.getSchemaName();
        //SCHEMAPATH = tempDB.getSchemaPath();
        SCHEMA = "pka";
        SCHEMAPATH = "pka";
        DBTYPE = tempDB.getDBType();
    }


    /**
    * Creates a new empty OfficeCal object
    */
    public OfficeCal () {
        ID = 0;
        init();
    }


    /**
    * Creates an OfficeCal object and uses the given id to populate it from the db
    *
    * @param id     The id of the OfficeCal to lookup from the db (int)
    * @param myConn Connection to the database
    */
    public OfficeCal (int id, DbConn myConn) {
        init();
        lookup(id, myConn);
    }


    /**
    * Retrieves a OfficeCal from the db and stores it in the current OfficeCal object
    *
    * @param id     The id  to lookup from the db (int)
    * @param myConn Connection to the database
    */
    public void getInfo (int id, DbConn myConn) {
        lookup(id, myConn);
    }


    /**
    * Retrieves a OfficeCal from the db and stores it in the current OfficeCal object
    *
    * @param id     The id to lookup from the db (int)
    * @param myConn Connection to the database
    */
    public void lookup (int id, DbConn myConn) {
        ID = 0;
        String outLine = "";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Connection conn = myConn.conn;

            // Create a Statement
            stmt = conn.createStatement ();

            String sqlcode = "SELECT id, client, refid, isclientapp, showedup, eventdate, begin, end, allday, sendreminder, ispublic, description " +
               "FROM " + SCHEMAPATH + ".calendar WHERE id=" + id;
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
            rs.next();
            int myID = rs.getInt(1);
            if (myID == id) {
                ID = myID;
                client = rs.getInt(2);
                clientObj = ((client !=0) ? new Clients(client, myConn) : null);
				refID = rs.getInt(3);
				isClientApp = (rs.getString(4).equals("T")) ? true : false;
				showedUp = (rs.getString(5).equals("T")) ? true : false;
				eventDate = (java.util.Date) rs.getTimestamp(6);
				begin = rs.getTime(7);
				end = rs.getTime(8);
				allDay = (rs.getString(9).equals("T")) ? true : false;
				sendReminder = (rs.getString(10).equals("T")) ? true : false;
				isPublic = (rs.getString(11).equals("T") ? true : false);
                description = rs.getString(12);

                //dateCreated = (java.util.Date) rs.getTimestamp();
                //dateUpdates = (java.util.Date) rs.getTimestamp();

                isNew = false;

            }
        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            System.out.println(outLine + " - OfficeCal lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - OfficeCal lookup");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            System.out.println(outLine + " - OfficeCal lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - OfficeCal lookup");
        }
        finally {
            if (rs != null)
                try { rs.close(); } catch (Exception i) {}
            if (stmt != null)
                try { stmt.close(); } catch (Exception i) {}
        }
    }


    /**
    * Retrieve the id for the current OfficeCal
    *
    * @return id    The id for the current OfficeCal
    */
    public int getID() {
        int id = ID;
        return(id);
    }

    public boolean isAllDay() {
        return allDay;
    }

    public Time getBegin() {
        return begin;
    }

    public int getClientID() {
        return client;
    }

    public Clients getClient() {
        return clientObj;
    }

    public String getDescription() {
        return description;
    }

    public Time getEnd() {
        return end;
    }

    public java.util.Date getEventDate() {
        return eventDate;
    }

    public boolean isClientApp() {
        return isClientApp;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public int getRefID() {
        return refID;
    }

    public boolean sendReminder() {
        return sendReminder;
    }

    public boolean showedUp() {
        return showedUp;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public void setBegin(Time begin) {
        this.begin = begin;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public void setEventDate(java.util.Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setIsClientApp(boolean isClientApp) {
        this.isClientApp = isClientApp;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setRefID(int refID) {
        this.refID = refID;
    }

    public void setSendReminder(boolean sendReminder) {
        this.sendReminder = sendReminder;
    }

    public void setShowedUp(boolean showedUp) {
        this.showedUp = showedUp;
    }



    /**
    * Save the current OfficeCal to the DB
    *
    * @param myConn     Connection to the database
    */
    public void save(DbConn myConn) {
       //save funciton
        String outLine = "";
        int rows;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sqlcode = "";
        try {

            Connection conn = myConn.conn;


            // Create a Statement
            stmt = conn.createStatement ();
            conn.setAutoCommit(false);
            if (isNew) {

                sqlcode = "INSERT INTO " + SCHEMAPATH + ".calendar (client, refid, isclientapp, showedup, eventdate, begin, end, allday, sendreminder, ispublic, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? " +
                    ")";
System.out.println(sqlcode);
                pstmt = conn.prepareStatement(sqlcode);
                int sqlID = 0;
                pstmt.setInt(++sqlID, client);
                pstmt.setInt(++sqlID, refID);
                pstmt.setString(++sqlID, (isClientApp) ? "T" : "F");
                pstmt.setString(++sqlID, (showedUp) ? "T" : "F");
                pstmt.setDate(++sqlID, ((eventDate != null) ? Utils.castDate(eventDate) : null));
                pstmt.setTime(++sqlID, begin);
                pstmt.setTime(++sqlID, end);
                pstmt.setString(++sqlID, (allDay) ? "T" : "F");
                pstmt.setString(++sqlID, (sendReminder) ? "T" : "F");
                pstmt.setString(++sqlID, (isPublic) ? "T" : "F");
                pstmt.setString(++sqlID, description);
System.out.println("OfficeCal - save - Got here 1");
                rows = pstmt.executeUpdate();
                isNew = false;
                // get inserted ID
                sqlcode = "SELECT last_insert_id() FROM " + SCHEMAPATH + ".calendar";
                rs = stmt.executeQuery(sqlcode);
                rs.next();
                ID = rs.getInt(1);
                rs.close();
                //sqlcode = "UPDATE " + SCHEMAPATH + ".calendar SET creationdate=now(), revisiondate=now() ";
                //sqlcode += "WHERE id = " + ID;
//System.out.println(sqlcode);
                //pstmt = conn.prepareStatement(sqlcode);
                //rows = pstmt.executeUpdate();
// do auth stuff
            } else {
                sqlcode = "UPDATE " + SCHEMAPATH + ".calendar " +
                      "SET client=?, refid=?, eventdate=?, begin=?, end=?, allday=?, sendreminder=?, ispublic=?, description=?";
                sqlcode += "WHERE id = " + ID +
                      "";
System.out.println(sqlcode);
                pstmt = conn.prepareStatement(sqlcode);
                int sqlID = 0;
                pstmt.setInt(++sqlID, client);
                pstmt.setInt(++sqlID, refID);
                pstmt.setString(++sqlID, (isClientApp) ? "T" : "F");
                pstmt.setString(++sqlID, (showedUp) ? "T" : "F");
                pstmt.setDate(++sqlID, ((eventDate != null) ? Utils.castDate(eventDate) : null));
                pstmt.setTime(++sqlID, begin);
                pstmt.setTime(++sqlID, end);
                pstmt.setString(++sqlID, (allDay) ? "T" : "F");
                pstmt.setString(++sqlID, (sendReminder) ? "T" : "F");
                pstmt.setString(++sqlID, (isPublic) ? "T" : "F");
                pstmt.setString(++sqlID, description);

System.out.println("OfficeCal - save - Got here 2");
                rows = pstmt.executeUpdate();
            }
            isNew = false;
            conn.commit();
        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            System.out.println(outLine + " - OfficeCal save");
            //com.pka.da.util.db.ALog.logError(myConn, userID,"N/A",0, outLine + " - OfficeCal save");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            System.out.println(outLine + " - OfficeCal save");
            //com.pka.da.util.db.ALog.logError(myConn, userID,"N/A",0, outLine + " - OfficeCal save");
        }
        finally {
            if (rs != null)
                try { rs.close(); } catch (Exception i) {}
            if (stmt != null)
                try { stmt.close(); } catch (Exception i) {}
            if (pstmt != null)
                try { pstmt.close(); } catch (Exception i) {}
        }
// do auth stuff
    }


    /**
    * Get a list of OfficeCal from the DB
    *
    * @param myConn     Connection to the database
    */
    public static OfficeCal[] getItemList(DbConn myConn) {
        return getItemList(myConn, "eventdat, begin", null, null);
    }


    /**
    * Get a list of OfficeCal from the DB
    *
    * @param myConn     Connection to the database
    */
    public static OfficeCal[] getItemListToday(DbConn myConn) {
        return getItemList(myConn, "eventdate, begin", new java.util.Date(), new java.util.Date());
    }


    /**
    * Get a list of OfficeCal from the DB
    *
    * @param myConn     Connection to the database
    * @param myDate     Date to retrieve
    */
    public static OfficeCal[] getItemListForDay(DbConn myConn, java.util.Date myDate) {
        return getItemList(myConn, "eventdate, begin", myDate, myDate);
    }


    /**
    * Get a list of OfficeCal from the DB
    *
    * @param myConn     Connection to the database
    * @param sort       A string defining the sort to be put on the returned values ('none' will do no sort)
    * @param beginDate  Starting date to retrieve
    * @param endDate    Ending date to retrieve
    */
    public static OfficeCal[] getItemList(DbConn myConn, String sort, java.util.Date beginDate, java.util.Date endDate) {
        //init();
        String outLine = "";
        ResultSet rs = null;
        Statement stmt = null;
        int returnSize = 0;
        String sqlcode = "";
        String sqlFrom = "";
        String sqlWhere = "";
        String sqlOrderBy = "";
        OfficeCal[] item = null;
        try {
            Connection conn = myConn.conn;

            // Create a Statement
            stmt = conn.createStatement ();
            //String mySchemaPath = myConn.getSchemaPath();
            String mySchemaPath = "pka";

            //sqlFrom = " FROM " + SCHEMAPATH + ".restaurant ";
            //sqlFrom = " FROM " + myConn.getSchemaPath() + ".restaurant ";
            sqlFrom = " FROM " + mySchemaPath + ".calendar ";
            sqlWhere = " WHERE 1=1 ";
            sqlWhere += (beginDate != null) ? "AND DATE_FORMAT(eventdate, '%Y-%m-%d') <= '" + Utils.dateToString(beginDate, "yyyy-MM-dd") + "' " : "";
            sqlWhere += (endDate != null) ? "AND DATE_FORMAT(eventdate, '%Y-%m-%d') >= '" + Utils.dateToString(endDate, "yyyy-MM-dd") + "' " : "";

            sqlOrderBy = (!sort.equals("none")) ? " ORDER BY " + sort + " " : "";

            sqlcode = "SELECT count(*) " + sqlFrom + sqlWhere;
System.out.println("begin date: " + beginDate + ", end date: " + endDate);
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
System.out.println("OfficeCal-getItemList-Got here 1");
            rs.next();
            returnSize = rs.getInt(1);
            rs.close();
            item = new OfficeCal[returnSize];

            sqlcode = "SELECT id, client, refid, isclientapp, showedup, eventdate, begin, end, allday, sendreminder, ispublic, description " +
                sqlFrom + sqlWhere + " " + sqlOrderBy + " ";
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
            int i = 0;
            while (rs.next()) {
                item[i] = new OfficeCal();
                item[i].ID = rs.getInt(1);
                item[i].client = rs.getInt(2);
                item[i].clientObj = ((item[i].client !=0) ? new Clients(item[i].client, myConn) : null);
				item[i].refID = rs.getInt(3);
				item[i].isClientApp = (rs.getString(4).equals("T")) ? true : false;
				item[i].showedUp = (rs.getString(5).equals("T")) ? true : false;
				item[i].eventDate = (java.util.Date) rs.getTimestamp(6);
				item[i].begin = rs.getTime(7);
				item[i].end = rs.getTime(8);
				item[i].allDay = (rs.getString(9).equals("T") ? true : false);
				item[i].sendReminder = (rs.getString(10).equals("T") ? true : false);
				item[i].isPublic = (rs.getString(11).equals("T") ? true : false);
                item[i].description = rs.getString(12);

                item[i].isNew = false;
                i++;
            }
            rs.close();

        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            //log(outLine);
            System.out.println(outLine + " - OfficeCal list lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - OfficeCal list lookup");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            //log(outLine);
            System.out.println(outLine + " - OfficeCal list lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine);
        }
        finally {
            if (rs != null)
                try { rs.close(); } catch (Exception i) {}
            if (stmt != null)
                try { stmt.close(); } catch (Exception i) {}
        }

        return item;
    }




}
