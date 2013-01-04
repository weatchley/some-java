package net.paulinealwesnmd.util.db;

// Support classes
import java.io.*;
import java.util.*;
import java.sql.*;
import java.math.*;
import java.awt.*;
import java.lang.Class.*;
import net.paulinealwesnmd.util.db.*;
import com.goldenop.util.db.*;
import com.goldenop.util.misc.*;
import javax.naming.*;
//import com.goldenop.util.misc.*;

/**
* DbConn is the class for managing db connections
*
* @author   Bill Atchley
*/
public class DbConn {
    public String schema = null;
    public String server = null;
    public Connection conn = null;
    private Properties props;
    private boolean propsLoaded = false;
    private String mysqlAddress = null;

    /**
    * Creates a new DbConn object given a schema
    *
    * @param schemaIN     The schema to connect to (String)
    */
    public DbConn (String schemaIN) {
//System.out.println("DbConn.java-Got Here 1");
        if (schemaIN.toLowerCase().equals("dummy")) {
            getProps();
        } else {
            schema = schemaIN;
            conn = getNew();
        }
    }

    /**
    * Creates a new DbConn object
    *
    */
    public DbConn () {
        getProps();
        schema = (String) props.getProperty("cs.schema.user");
        conn = getNew();
    }

    /**
    * Creates a new dummy DbConn object
    *
    * @param temp     Temp flag
    */
    public DbConn (boolean isTemp) {
        getProps();
    }

    /**
    * Creates a new DbConn object given a connection
    *
    * @param connIN     The preestablished connection
    */
    public DbConn (Connection connIN) {
        conn = connIN;
        getProps();
    }


    private void getProps() {
        InputStream in = null;
        try {
            if (!propsLoaded) {
                String ProductionStatus = "development";
                try {
                    Context initCtx = new InitialContext();
                    ProductionStatus = (String) initCtx.lookup("java:comp/env/ProductionStatus");
                    mysqlAddress = (String) initCtx.lookup("java:comp/env/mysqlAddress");
                    //InputStream in = new Class.getResourceAsStream ("cs.properties");
                }
                catch (Exception e) {}

                String propertiesFile = null;
                if (ProductionStatus.toLowerCase().equals("prod") || ProductionStatus.toLowerCase().equals("production")) {
                    propertiesFile = "csprod.properties";
                    //System.out.println("connecting to production database...");
                } else {
                    //try to see the Props param is defined in environment variables
                        try {
						  if (1==2) {
                            java.util.Properties p = OSEnvironment.get();
                                //p.list(System.out);
                                //System.out.println("the current value of CSIPS is : " +
                                //p.getProperty("CSIPS"));
                            ProductionStatus = p.getProperty("CSIPS");
                            if (ProductionStatus != null && (ProductionStatus.toLowerCase().equals("prod") || ProductionStatus.toLowerCase().equals("production"))) {
                                propertiesFile = "csprod.properties";
                                //System.out.println("connecting to production database...");
                            }else{
                                propertiesFile = "csdev.properties";
                                //System.out.println("connecting to development database...");
                            }
					      }
                        }
                        catch (Throwable e) {
                            e.printStackTrace();
                            propertiesFile = "csdev.properties";
                        }
                    //propertiesFile = "csdev.properties";
                }
                    //System.out.println("connection using: " + propertiesFile);
                in = DbConn.class.getResourceAsStream (propertiesFile);
                //in = Class.getResourceAsStream (propertiesFile);
                props = new Properties();
                props.load(in);
                propsLoaded = true;
            }
        }
        catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        finally {
            try { in.close(); } catch (Exception i) {}
        }

    }

    /**
    * Make a coonnection to the db
    * @return myconn     The db connection handle (Connection)
    */
    public Connection getNew() {
        String schemapass = null;
        String key = null;
        Connection myconn = null;
        String url = null;
        try {
//System.out.println("DbConn.java-getNew-Got Here 1");
            //Context initCtx = new InitialContext();
            getProps();
//System.out.println("DbConn.java-getNew-Got Here 1.1");
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//System.out.println("DbConn.java-getNew-Got Here 1.2");
            //server = (String) props.getProperty("DBServer");
            //server = "127.0.0.1:3306";
            //server = "localhost";
            server = mysqlAddress;

            //schemapass = (String) props.getProperty("cs.schema");
            schemapass = "075065031066067";
            //key = (String) props.getProperty("cs.k");
            key = "q5o26ik35hitgsvm7llfupmebxje5ruh";
            String pass = AbcCrypt.doDecrypt(schemapass, key, 5);
            //url = "jdbc:mysql://" + server + "/"+ schema + "?user=pkauser&password=" + pass + "&zeroDateTimeBehavior=convertToNull";
            url = "jdbc:mysql://" + server + "/"+ schema + "?user=pkauser&password=" + pass;
            //url = "jdbc:mysql://" + server + "/"+ schema;
            //myconn = DriverManager.getConnection (url);
            //myconn = DriverManager.getConnection (url,"pkauser",pass);
            Properties p = new Properties();
            p.put("user","pkauser");
            p.put("password","drpka");

//System.out.println("DbConn.java-getNew-Got Here 2 - Connection string: " + url);
            myconn = DriverManager.getConnection (url);
            //myconn = DriverManager.getConnection (url, p);
//System.out.println("DbConn.java-getNew-Got Here 2.5");
        }
        catch (java.sql.SQLException e) {
            System.out.println(e + e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
//System.out.println("DbConn.java-getNew-Got Here 3");
        return(myconn);
    }

    /**
    * Release a coonnection to the db
    */
    public void release() {
        try {
            conn.close();
            //System.out.println("Connection Closed");
        }
        catch (java.sql.SQLException e) {
            System.out.println(e + e.getMessage());
        }
    }

    /**
    * Get connection
    *
    * @return connection
    */
    public Connection getConn() {
        return(conn);
    }

    /**
    * Get schema name
    *
    * @return Schema name
    */
    //public static String getSchemaName() {
    public String getSchemaName() {
        String name = null;
        try {
            getProps();
            name = (String) props.getProperty("cs.schema.user");
        }
        catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return(name);
    }

    /**
    * Get schema path
    *
    * @return Schema path
    */
    //public static String getSchemaPath() {
    public String getSchemaPath() {
        String path = null;
        try {
            getProps();
            path = (String) props.getProperty("cs.schema.path");
        }
        catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return(path);
    }

    /**
    * Get db type
    *
    * @return db type
    */
    //public static String getDBType() {
    public String getDBType() {
        String dbType = null;
        try {
            getProps();
            dbType = (String) props.getProperty("MainDBServer");
        }
        catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return(dbType);
    }

}
