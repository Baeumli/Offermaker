/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offermaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Baeumli
 */
public class Database {

    private static Connection cn = null;
    private static Database instance = null;
    private final String DB_CONNECTION_STRING = "jdbc:mysql://206.189.61.6/jclerk";
//            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //Exception when connection timeout
    public Connection establishConnection(String username, String password) {
        if (cn == null) {
            try {
                cn = DriverManager.getConnection(DB_CONNECTION_STRING, username, password);

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

    //Adds Data to database
    //ID gets auto-incremented by the database
    public void addPerson(String sex, String firstname, String lastname, String email, String phone, String company, String street, String city, String zip) {
        try {
            String sql = "INSERT INTO `person` VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, sex);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, company);
            ps.setString(7, street);
            ps.setString(8, city);
            ps.setString(9, zip);
            ps.execute();
        } catch (SQLException ex) {
            System.err.println("Failed to add person");
            ex.printStackTrace();
        }
    }
    
    //Removes data from database
    public void removePerson(int id) {
        try {
            String sql = "DELETE FROM `person` WHERE id = ?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Failed to delete person");
            ex.printStackTrace();
        }
    }

        public void editPerson(String sex, String firstname, String lastname, String email, String phone, String company, String street, String city, String zip, int id) {
        try {
            String sql = "UPDATE `person` SET sex = ?, firstname = ?, lastname = ?, email = ?, phone = ?, company = ?, street = ?, city = ?, zip = ? WHERE id = ?;";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, sex);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, company);
            ps.setString(7, street);
            ps.setString(8, city);
            ps.setString(9, zip);
            ps.setInt(10, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Failed to edit person");
            ex.printStackTrace();
        }
    }
         
    public void addProduct(String brand, String name, double price) {
        try {
            String sql = "INSERT INTO `product` VALUES (NULL, ?, ?, ?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.execute();
        } catch (SQLException ex) {
            System.err.println("Failed to add product");
            ex.printStackTrace();
        }
    }


    
    
    public void removeProduct(int id) {
        try {
            String sql = "DELETE FROM `product` WHERE id = ?;";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Failed to delete product");
            ex.printStackTrace();
        }
    }

    
            public void editProduct(String brand, String productName, String price, int id) {
        try {
            String sql = "UPDATE `product` SET brand = ?, name = ?, price = ? WHERE id = ?;";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, productName);
            ps.setString(3, price);
            ps.setInt(4, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Failed to edit product");
            ex.printStackTrace();
        }
    }
    
    //Gets data from database
    public ObservableList<Person> getPersons() {
        
        Person person;
        ObservableList<Person> persons = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM `person`";
            PreparedStatement ps = cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("sex"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("phone"), rs.getString("company"), rs.getString("street"), rs.getString("city"), rs.getString("zip"), rs.getInt("id"));
                persons.add(person);
            }
            return persons;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList<Product> getProducts() {
        Product product;
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM `product`";
            PreparedStatement ps = cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getString("brand"), rs.getString("name"), rs.getDouble("price"), rs.getInt("id"));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Connection getCn() {
        return cn;
    }
    
    
}
