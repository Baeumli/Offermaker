/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Baeumli
 */
public class Database {
    
    private Connection cn = null;
    private static Database instance = null;
    private final String USERNAME = "db_user";
    private final String PASSWORD = "lookatallthesechickens";
    private final String DB_CONNECTION_STRING = "jdbc:mysql://baeumli.internet-box.ch/fra_db"
            //Need to set TimeZone for some weird reason
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    //Exception when connection timeout
    public Connection establishConnection() {
        if (cn == null) {

            try {
                cn = DriverManager.getConnection(DB_CONNECTION_STRING, USERNAME, PASSWORD);                
            } catch (SQLException ex) {
                System.out.println("Something went wrong while trying to establish a connection to the database.");
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

    
    //ID gets auto-incremented by the database
    public void addPerson(int personid, String sex, String firstname, String lastname, String email, String phone, String company) {
        try {
            String sql = "INSERT INTO `person` VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, personid);
            ps.setString(2, sex);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, company);
            
            ps.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    
        public void addProduct(int productid, String brand, String name, int price) {
        try {
            String sql = "INSERT INTO `product` VALUES (NULL, ?, ?, ?, ?, ?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, productid);
            ps.setString(2, brand);
            ps.setString(3, name);
            ps.setInt(4, price);
            
            ps.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }  
}
