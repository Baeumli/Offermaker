/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven;

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

    private Connection cn = null;
    private static Database instance = null;
    private final String USERNAME = "db_user";
    private final String PASSWORD = "lookatallthesechickens";
    private final String DB_CONNECTION_STRING = "jdbc:mysql://eggenberg.mynetgear.com/franz_db"
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

    //Adds Data to database
    //ID gets auto-incremented by the database
    public void addPerson(String sex, String firstname, String lastname, String email, String phone, String company) {
        try {
            String sql = "INSERT INTO `person` VALUES (NULL, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, sex);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, company);
            ps.execute();
        } catch (SQLException ex) {
            System.err.println("Failed to add person");
            ex.printStackTrace();
        }
    }

    public void addProduct(String brand, String name, double price) {
        try {
            String sql = "INSERT INTO `product` VALUES (NULL, ?, ?, ?, ?);";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.execute();
        } catch (SQLException ex) {
            System.err.println("Failed to add product");
        }
    }

    //Removes data from database
    public void removePerson(int id) {
        try {
            String sql = "DELETE FROM `person` WHERE person_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
        } catch (SQLException ex) {
            System.out.println("Failed to delete person");
            ex.printStackTrace();
        }
    }

    public void removeProduct(int id) {
        try {
            String sql = "DELETE FROM `product` WHERE product_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
        } catch (SQLException ex) {
            System.out.println("Failed to delete product");
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
                person = new Person(rs.getString("sex"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("phone"), rs.getString("company"), rs.getInt("person_id"));
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
                product = new Product(rs.getString("brand"), rs.getString("name"), rs.getDouble("price"), rs.getInt("product_id"));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
