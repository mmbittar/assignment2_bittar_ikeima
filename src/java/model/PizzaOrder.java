/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Creates PizzaOrder object to be stored into database
 *
 * @author bittar_ikeima
 */
public class PizzaOrder {

    private int id;
    private String name, phone, pizzaSize, toppings;
    private boolean delivery;
    private double price;

    public PizzaOrder(int id, String name, String phone, String pizzaSize, String toppings, boolean delivery, double price) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.pizzaSize = pizzaSize;
        this.toppings = toppings;
        this.delivery = delivery;
        this.price = price;
    }

    public PizzaOrder(String name, String phone, String pizzaSize, String toppings, boolean delivery, double price) {
        this.name = name;
        this.phone = phone;
        this.pizzaSize = pizzaSize;
        this.toppings = toppings;
        this.delivery = delivery;
        this.price = price;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the pizzaSize
     */
    public String getPizzaSize() {
        return pizzaSize;
    }

    /**
     * @param pizzaSize the pizzaSize to set
     */
    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    /**
     * @return the toppings
     */
    public String getToppings() {
        return toppings;
    }

    /**
     * @param toppings the toppings to set
     */
    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    /**
     * @return the delivery
     */
    public boolean isDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String method = "Pick Up";
        if (delivery) {
            method = "Delivery";
        }

        return "Your " + method + " Order:"
                + "<br>Customer: " + name
                + "<br>Phone: " + phone
                + "<br>Pizza Size: " + pizzaSize
                + "<br>Toppings: " + toppings
                + "<br>Total: $" + String.format("%.2f", price);
    }

}
