/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Baeumli
 */
public class Database {
    
    private static Database instance = null;
    private final String USERNAME = "db_user";
    private final String PASSWORD = "lookatallthesechickens";
    private final String DB_CONNECTION_STRING = "jdbc:mysql://baeumli.internet-box.ch/fra_db"
            //Need to set TimeZone for some weird reason
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection cn = null;
    
    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public Connection establishConnection() {
        if (cn == null) {

            try {
                cn = DriverManager.getConnection(DB_CONNECTION_STRING, USERNAME, PASSWORD);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cn;
    }

    public void closeConnection() {
        try {
            cn.close();
            cn = null;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void insertInto(String table, String values) {
        try {
            Statement st = cn.createStatement();
            st.executeUpdate("INSERT INTO " + table + " VALUES (" + values + ");");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }  
}
