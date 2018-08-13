/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offermaker;

/**
 *
 * @author lucae
 */
public class Person {
    private String sex, firstname, lastname, email, phone, company, street, city, zip;
    private int id;
    
    public Person(String sex, String firstname, String lastname, String email, String phone, String company, String street, String city, String zip, int id){
        this.sex = sex;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.street = street;
        this.city = city;
        this.zip = zip;  
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }
    
    public int getId(){
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }
    
    
    
}
