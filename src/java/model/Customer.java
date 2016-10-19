/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Creates Customer object to be stored in the session
 *
 * @author bittar_ikeima
 */
public class Customer {

    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Gets customer name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Customer name
     *
     * @param name is customers name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets customers phone
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets customers phone
     *
     * @param phone is customers phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
