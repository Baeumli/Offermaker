/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven;

/**
 *
 * @author lucae
 */
public class Product {
    private String brand, name;
    private double price;
    private int id;
    
    public Product(String brand, String name, double price, int id){
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public int getId(){
        return id;
    }
}
