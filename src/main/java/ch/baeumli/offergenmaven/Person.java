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
public class Person {
    private String sex, firstname, lastname, email, phone, company;
    
    public Person(String sex, String firstname, String lastname, String email, String phone, String company){
        this.sex = sex;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.company = company;
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
    
}
