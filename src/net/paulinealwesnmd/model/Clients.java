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
* Clients is the class for clients in the db
*
* @author   Bill Atchley
*/
public class Clients {
    private int ID = 0;
    private String lastName = null;
    private String firstName = null;
    private String middleName = null;
    private String occupation = null;
    private String email = null;
    private String address = null;
    private String city = null;
    private String state = null;
    private String zip = null;
    private String phone = null;
    private String fax = null;
    private java.util.Date lastseen = null;
    private String location = null;
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
    * Creates a new empty Clients object
    */
    public Clients () {
        ID = 0;
        init();
    }


    /**
    * Creates an Clients object and uses the given id to populate it from the db
    *
    * @param id     The id of the Clients to lookup from the db (int)
    * @param myConn Connection to the database
    */
    public Clients (int id, DbConn myConn) {
        init();
        lookup(id, myConn);
    }


    /**
    * Retrieves a Clients from the db and stores it in the current Clients object
    *
    * @param id     The id  to lookup from the db (int)
    * @param myConn Connection to the database
    */
    public void getInfo (int id, DbConn myConn) {
        lookup(id, myConn);
    }


    /**
    * Retrieves a Clients from the db and stores it in the current Clients object
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

            String sqlcode = "SELECT id, lastname, firstname, middlename, occupation, email, address, city, state, zip, phone, fax, lastseen, " +
                "location, notes FROM " + SCHEMAPATH + ".clients WHERE id=" + id;
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
            rs.next();
            int myID = rs.getInt(1);
            if (myID == id) {
                ID = myID;
                lastName = rs.getString(2);
                firstName = rs.getString(3);
                middleName = rs.getString(4);
                occupation = rs.getString(5);
                email = rs.getString(6);
                address = rs.getString(7);
                city = rs.getString(8);
                state = rs.getString(9);
                zip = rs.getString(10);
                phone = rs.getString(11);
                fax = rs.getString(12);
                lastseen = (java.util.Date) rs.getTimestamp(13);
                location = rs.getString(14);
                notes = rs.getString(15);

                //dateCreated = (java.util.Date) rs.getTimestamp();
                //dateUpdates = (java.util.Date) rs.getTimestamp();

                isNew = false;

            }
        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            System.out.println(outLine + " - Clients lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - Clients lookup");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            System.out.println(outLine + " - Clients lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - Clients lookup");
        }
        finally {
            if (rs != null)
                try { rs.close(); } catch (Exception i) {}
            if (stmt != null)
                try { stmt.close(); } catch (Exception i) {}
        }
    }


    /**
    * Retrieve the id for the current Clients
    *
    * @return id    The id for the current Clients
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public java.util.Date getLastseen() {
        return lastseen;
    }

    public String getLocation() {
        return location;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNotes() {
        return notes;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getFullName() {
		return firstName + " " + ((middleName != null) ? middleName + " " : "") + lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastseen(java.util.Date lastseen) {
        this.lastseen = lastseen;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }



    /**
    * Save the current Clients to the DB
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

                sqlcode = "INSERT INTO " + SCHEMAPATH + ".clients (lastname, firstname, middlename, occupation, email, address, city, state, zip, phone, fax, lastseen, " +
                "location, notes) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
                    ")";
System.out.println(sqlcode);
                pstmt = conn.prepareStatement(sqlcode);
                int sqlID = 0;
                pstmt.setString(++sqlID, lastName);
                pstmt.setString(++sqlID, firstName);
                pstmt.setString(++sqlID, middleName);
                pstmt.setString(++sqlID, occupation);
                pstmt.setString(++sqlID, email);
                pstmt.setString(++sqlID, address);
                pstmt.setString(++sqlID, city);
                pstmt.setString(++sqlID, state);
                pstmt.setString(++sqlID, zip);
                pstmt.setString(++sqlID, phone);
                pstmt.setString(++sqlID, fax);
                pstmt.setDate(++sqlID, ((lastseen != null) ? Utils.castDate(lastseen) : null));
                pstmt.setString(++sqlID, location);
                pstmt.setString(++sqlID, notes);
System.out.println("Clients - save - Got here 1");
                rows = pstmt.executeUpdate();
                isNew = false;
                // get inserted ID
                sqlcode = "SELECT last_insert_id() FROM " + SCHEMAPATH + ".clients";
                rs = stmt.executeQuery(sqlcode);
                rs.next();
                ID = rs.getInt(1);
                rs.close();
                //sqlcode = "UPDATE " + SCHEMAPATH + ".clients SET creationdate=now(), revisiondate=now() ";
                //sqlcode += "WHERE id = " + ID;
//System.out.println(sqlcode);
                //pstmt = conn.prepareStatement(sqlcode);
                //rows = pstmt.executeUpdate();
// do auth stuff
            } else {
                sqlcode = "UPDATE " + SCHEMAPATH + ".clients " +
                      "SET lastname=?, firstname=?, middlename=?, occupation=?, email=?, address=?, city=?, state=?, zip=?," +
                      "phone=?, fax=?, lastseen=?, location=?, notes=? ";
                sqlcode += "WHERE id = " + ID +
                      "";
System.out.println(sqlcode);
                pstmt = conn.prepareStatement(sqlcode);
                int sqlID = 0;
                pstmt.setString(++sqlID, lastName);
                pstmt.setString(++sqlID, firstName);
                pstmt.setString(++sqlID, middleName);
                pstmt.setString(++sqlID, occupation);
                pstmt.setString(++sqlID, email);
                pstmt.setString(++sqlID, address);
                pstmt.setString(++sqlID, city);
                pstmt.setString(++sqlID, state);
                pstmt.setString(++sqlID, zip);
                pstmt.setString(++sqlID, phone);
                pstmt.setString(++sqlID, fax);
System.out.println("Clients - save - Got here 2");
                pstmt.setDate(++sqlID, ((lastseen != null) ? Utils.castDate(lastseen) : null));
                pstmt.setString(++sqlID, location);
                pstmt.setString(++sqlID, notes);
System.out.println("Clients - save - Got here 3");
                rows = pstmt.executeUpdate();
            }
            isNew = false;
            conn.commit();
        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            System.out.println(outLine + " - Clients save");
            //com.pka.da.util.db.ALog.logError(myConn, userID,"N/A",0, outLine + " - Clients save");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            System.out.println(outLine + " - Clients save");
            //com.pka.da.util.db.ALog.logError(myConn, userID,"N/A",0, outLine + " - Clients save");
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
    * Get a list of Clients from the DB
    *
    * @param myConn     Connection to the database
    */
    public static Clients[] getItemList(DbConn myConn) {
        return getItemList(myConn, "lastname, firstname");
    }


    /**
    * Get a list of Clients from the DB
    *
    * @param myConn     Connection to the database
    * @param sort       A string defining the sort to be put on the returned values ('none' will do no sort)
    */
    public static Clients[] getItemList(DbConn myConn, String sort) {
        //init();
        String outLine = "";
        ResultSet rs = null;
        Statement stmt = null;
        int returnSize = 0;
        String sqlcode = "";
        String sqlFrom = "";
        String sqlWhere = "";
        String sqlOrderBy = "";
        Clients[] item = null;
        try {
            Connection conn = myConn.conn;

            // Create a Statement
            stmt = conn.createStatement ();
            //String mySchemaPath = myConn.getSchemaPath();
            String mySchemaPath = "pka";

            //sqlFrom = " FROM " + SCHEMAPATH + ".restaurant ";
            //sqlFrom = " FROM " + myConn.getSchemaPath() + ".restaurant ";
            sqlFrom = " FROM " + mySchemaPath + ".clients ";
            sqlWhere = " WHERE 1=1 ";

            sqlOrderBy = (!sort.equals("none")) ? " ORDER BY " + sort + " " : "";

            sqlcode = "SELECT count(*) " + sqlFrom + sqlWhere;
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
System.out.println("Clients-getItemList-Got here 1");
            rs.next();
            returnSize = rs.getInt(1);
            rs.close();
            item = new Clients[returnSize];

            sqlcode = "SELECT id,lastname, firstname, middlename, occupation, email, address, city, state, zip, phone, fax, lastseen, " +
                "location, notes " + sqlFrom + sqlWhere + " " + sqlOrderBy + " ";
System.out.println(sqlcode);
            rs = stmt.executeQuery(sqlcode);
            int i = 0;
            while (rs.next()) {
                item[i] = new Clients();
                item[i].ID = rs.getInt(1);
                item[i].lastName = rs.getString(2);
                item[i].firstName = rs.getString(3);
                item[i].middleName = rs.getString(4);
                item[i].occupation = rs.getString(5);
                item[i].email = rs.getString(6);
                item[i].address = rs.getString(7);
                item[i].city = rs.getString(8);
                item[i].state = rs.getString(9);
                item[i].zip = rs.getString(10);
                item[i].phone = rs.getString(11);
                item[i].fax = rs.getString(12);
                item[i].lastseen = (java.util.Date) rs.getTimestamp(13);
                item[i].location = rs.getString(14);
                item[i].notes = rs.getString(15);

                item[i].isNew = false;
                i++;
            }
            rs.close();

        }
        catch (SQLException e) {
            outLine = outLine + "SQLException caught: " + e.getMessage();
            //log(outLine);
            System.out.println(outLine + " - Clients list lookup");
            //com.pka.da.util.db.ALog.logError(myConn, 0,"N/A",0, outLine + " - Clients list lookup");
        }

        catch (Exception e) {
            outLine = outLine + "Exception caught: " + e.getMessage();
            //log(outLine);
            System.out.println(outLine + " - Clients list lookup");
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
