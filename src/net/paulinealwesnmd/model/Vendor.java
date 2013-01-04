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
* Vendor is the class for clients in the db
*
* @author   Bill Atchley
*/
public class Vendor {
    private int ID = 0;
    private String name = null;
    private String address = null;
    private String city = null;
    private String state = null;
    private String zip = null;
    private String phone = null;
    private String fax = null;
    private String email = null;
    private String website = null;
    private boolean isActive = false;
    private String notes = null;
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
    * Creates a new empty Vendor object
    */
    public Vendor () {
        ID = 0;
        init();
    }


    /**
    * Creates an Vendor object and uses the given id to populate it from the db
    *
    * @param id     The id of the Vendor to lookup from the db (int)
    * @param myConn Connection to the database
    */
    public Vendor (int id, DbConn myConn) {
        init();
        lookup(id, myConn);
    }


    /**
    * Retrieves a Vendor from the db and stores it in the current Vendor object
    *
    * @param id     The id  to lookup from the db (int)
    * @param myConn Connection to the database
    */
    public void getInfo (int id, DbConn myConn) {
        lookup(id, myConn);
    }


    /**
    * Retrieves a Vendor from the db and stores it in the current Vendor object
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

            String sqlcode = "SELECT id, name, address, city, state, zip, phone, fax, email, website, " +
                "isactive, notes FROM " + SCHEMAPATH + ".vendors WHERE id=" + id;
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
            rs.next();
            int myID = rs.getInt(1);
            if (myID == id) {
                ID = myID;
                name = rs.getString(2);
                address = rs.getString(3);
                city = rs.getString(4);
                state = rs.getString(5);
                zip = rs.getString(6);
                phone = rs.getString(7);
                fax = rs.getString(8);
                email = rs.getString(9);
                website = rs.getString(10);
                isActive = (rs.getString(11).equals("T") ? true : false);
                notes = rs.getString(12);

                //dateCreated = (java.util.Date) rs.getTimestamp();
                //dateUpdates = (java.util.Date) rs.getTimestamp();

                isNew = false;

            }
        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            System.out.println(outLine + " - Vendor lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - Vendor lookup");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            System.out.println(outLine + " - Vendor lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - Vendor lookup");
        }
        finally {
            if (rs != null)
                try { rs.close(); } catch (Exception i) {}
            if (stmt != null)
                try { stmt.close(); } catch (Exception i) {}
        }
    }


    /**
    * Retrieve the id for the current Vendor
    *
    * @return id    The id for the current Vendor
    */
    public int getID() {
        int id = ID;
        return(id);
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public String getWebsite() {
        return website;
    }

    public String getZip() {
        return zip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }




    /**
    * Save the current Vendor to the DB
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

                sqlcode = "INSERT INTO " + SCHEMAPATH + ".vendors (name, address, city, state, zip, phone, fax, email, website, " +
                "isactive, notes) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
                    ")";
System.out.println(sqlcode);
                pstmt = conn.prepareStatement(sqlcode);
                int sqlID = 0;
                pstmt.setString(++sqlID, name);
                pstmt.setString(++sqlID, address);
                pstmt.setString(++sqlID, city);
                pstmt.setString(++sqlID, state);
                pstmt.setString(++sqlID, zip);
                pstmt.setString(++sqlID, phone);
                pstmt.setString(++sqlID, fax);
                pstmt.setString(++sqlID, email);
                pstmt.setString(++sqlID, website);
                pstmt.setString(++sqlID, ((isActive) ? "T" : "F"));
                pstmt.setString(++sqlID, notes);
System.out.println("Vendor - save - Got here 1");
                rows = pstmt.executeUpdate();
                isNew = false;
                // get inserted ID
                sqlcode = "SELECT last_insert_id() FROM " + SCHEMAPATH + ".vendors";
                rs = stmt.executeQuery(sqlcode);
                rs.next();
                ID = rs.getInt(1);
                rs.close();
                //sqlcode = "UPDATE " + SCHEMAPATH + ".vendors SET creationdate=now(), revisiondate=now() ";
                //sqlcode += "WHERE id = " + ID;
//System.out.println(sqlcode);
                //pstmt = conn.prepareStatement(sqlcode);
                //rows = pstmt.executeUpdate();
// do auth stuff
            } else {
                sqlcode = "UPDATE " + SCHEMAPATH + ".vendors " +
                      "SET name=?, address=?, city=?, state=?, zip=?," +
                      "phone=?, fax=?, email=?, website=?, isactive=?, notes=? ";
                sqlcode += "WHERE id = " + ID +
                      "";
System.out.println(sqlcode);
                pstmt = conn.prepareStatement(sqlcode);
                int sqlID = 0;
                pstmt.setString(++sqlID, name);
                pstmt.setString(++sqlID, address);
                pstmt.setString(++sqlID, city);
                pstmt.setString(++sqlID, state);
                pstmt.setString(++sqlID, zip);
                pstmt.setString(++sqlID, phone);
                pstmt.setString(++sqlID, fax);
                pstmt.setString(++sqlID, email);
                pstmt.setString(++sqlID, website);
                pstmt.setString(++sqlID, ((isActive) ? "T" : "F"));
                pstmt.setString(++sqlID, notes);
System.out.println("Vendor - save - Got here 2");
                rows = pstmt.executeUpdate();
            }
            isNew = false;
            conn.commit();
        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            System.out.println(outLine + " - Vendor save");
            //com.pka.da.util.db.ALog.logError(myConn, userID,"N/A",0, outLine + " - Vendor save");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            System.out.println(outLine + " - Vendor save");
            //com.pka.da.util.db.ALog.logError(myConn, userID,"N/A",0, outLine + " - Vendor save");
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
    * Get a list of Vendor from the DB
    *
    * @param myConn     Connection to the database
    */
    public static Vendor[] getItemList(DbConn myConn) {
        return getItemList(myConn, "none", true);
    }


    /**
    * Get a list of Vendor from the DB
    *
    * @param myConn     Connection to the database
    * @param activeOnly  Select if only active vendors will be displayed
    *
    */
    public static Vendor[] getItemList(DbConn myConn, boolean activeOnly) {
        return getItemList(myConn, "none", activeOnly);
    }


    /**
    * Get a list of Vendor from the DB
    *
    * @param myConn      Connection to the database
    * @param sort        A string defining the sort to be put on the returned values ('none' will do no sort)
    * @param activeOnly  Select if only active vendors will be displayed
    *
    */
    public static Vendor[] getItemList(DbConn myConn, String sort, boolean activeOnly) {
        //init();
        String outLine = "";
        ResultSet rs = null;
        Statement stmt = null;
        int returnSize = 0;
        String sqlcode = "";
        String sqlFrom = "";
        String sqlWhere = "";
        String sqlOrderBy = "";
        Vendor[] item = null;
        try {
            Connection conn = myConn.conn;

            // Create a Statement
            stmt = conn.createStatement ();
            //String mySchemaPath = myConn.getSchemaPath();
            String mySchemaPath = "pka";

            //sqlFrom = " FROM " + SCHEMAPATH + ".restaurant ";
            //sqlFrom = " FROM " + myConn.getSchemaPath() + ".restaurant ";
            sqlFrom = " FROM " + mySchemaPath + ".vendors ";
            sqlWhere = " WHERE 1=1 ";
            sqlWhere += (activeOnly) ? " AND isactive = 'T' " : "";

            sqlOrderBy = (!sort.equals("none")) ? " ORDER BY " + sort + " " : "";

            sqlcode = "SELECT count(*) " + sqlFrom + sqlWhere;
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
System.out.println("Vendor-getItemList-Got here 1");
            rs.next();
            returnSize = rs.getInt(1);
            rs.close();
            item = new Vendor[returnSize];

            sqlcode = "SELECT id, name, address, city, state, zip, phone, fax, email, website, " +
                "isactive, notes  " + sqlFrom + sqlWhere + " " + sqlOrderBy + " ";
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
            int i = 0;
            while (rs.next()) {
                item[i] = new Vendor();
                item[i].ID = rs.getInt(1);
                item[i].name = rs.getString(2);
                item[i].address = rs.getString(3);
                item[i].city = rs.getString(4);
                item[i].state = rs.getString(5);
                item[i].zip = rs.getString(6);
                item[i].phone = rs.getString(7);
                item[i].fax = rs.getString(8);
                item[i].email = rs.getString(9);
                item[i].website = rs.getString(10);
                item[i].isActive = (rs.getString(11).equals("T") ? true : false);
                item[i].notes = rs.getString(12);

                item[i].isNew = false;
                i++;
            }
            rs.close();

        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            //log(outLine);
            System.out.println(outLine + " - Vendor list lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - Vendor list lookup");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            //log(outLine);
            System.out.println(outLine + " - Vendor list lookup");
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
