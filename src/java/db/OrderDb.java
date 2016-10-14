/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author bittar_ikeima
 */
public class OrderDb {
    private int id;
    private String name;
    private String phone;
    private String pizzaSize;
    private String toppings;
    private boolean delivery;
    private double price;

    public OrderDb(int id, String name, String phone, String pizzaSize, String toppings, boolean delivery, double price) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.pizzaSize = pizzaSize;
        this.toppings = toppings;
        this.delivery = delivery;
        this.price = price;
    }

    public OrderDb(String name, String phone, String pizzaSize, String toppings, boolean delivery, double price) {
        this.name = name;
        this.phone = phone;
        this.pizzaSize = pizzaSize;
        this.toppings = toppings;
        this.delivery = delivery;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
