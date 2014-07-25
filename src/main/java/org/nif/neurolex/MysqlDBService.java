package org.nif.neurolex;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author wawong
 */
public class MysqlDBService {
    // setup connection values to the database

    static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static String URL = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";

    static {
        try {
            Class.forName(DB_DRIVER).newInstance();
        } catch (ClassNotFoundException cnfx) {
            cnfx.printStackTrace();
        } catch (IllegalAccessException iaex) {
            iaex.printStackTrace();
        } catch (InstantiationException iex) {
            iex.printStackTrace();
        }
    }

    /**
     * Returns a normal connection to the database
     */
    public static Connection getConnection() throws Exception {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public boolean readConfig(String path) throws Exception {
        try {

            System.err.println("-------------------------------------ReadConfig:" + path);
            //Now, go get the connection info from resource files
            ResourceBundle rb
                    = //Then get the real connection info from the real resource
                    rb = new PropertyResourceBundle(new FileInputStream(path));

            USERNAME = rb.getString("postgres_user");
            PASSWORD = rb.getString("postgres_password");
            URL = rb.getString("postgres_jdbc_url");//connectString+HOST+":"+PORT+":"+DATABASENAME;

            System.out.println("--------------Portal-URL------------" + URL);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Problem reading database parameters: " + e.getMessage());

        }

    }

    /**
     * Static method that releases a connection
     *
     * @param con the connection
     */
    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method that return a result set
     *
     * @param query
     * @return
     */
    public static ResultSet getResultSet(String query) throws Exception {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        // grab a connection to the database
        try {

            con = getConnection();
            if (con == null) {
                return null;
            }
            stmt = con.createStatement();
            // run the sql query to obtain a result set

            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

        return rs;

    }

    /**
     * Static method that return a result set
     *
     * @param query
     * @return
     */
    public static java.util.Map getTypeMap(MainFrame ms) {
        Connection con = null;
        Statement stmt = null;
        java.util.Map map = null;
        // grab a connection to the database
        try {
            con = getConnection();
            if (con == null) {
                return null;
            }
            map = con.getTypeMap();

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } catch (RuntimeException rex) {
            rex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
}
