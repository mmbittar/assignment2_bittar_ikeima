/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import model.PizzaOrder;

/**
 *
 * @author bittar_ikeima
 */
public class OrderDb {
    String driver;
    String connUrl;
    String database;
    String user;
    String password;
    
    private final String TABLE_NAME = "pizza_orders";
    private final String ID = "id";
    private final String NAME = "name";
    private final String PHONE = "phone";
    private final String PIZZA_SIZE = "pizzaSize";
    private final String TOPPINGS = "toppings";
    private final String DELIVERY = "delivery";
    private final String PRICE = "price";
    
    public int createOrder (PizzaOrder order) throws Exception{
        String formatSql = "INSERT INTO %s (%s, %s, %s, %s, %s, %s) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        String sql = String.format(formatSql, TABLE_NAME, NAME, PHONE, 
                PIZZA_SIZE, TOPPINGS, DELIVERY, PRICE);
        Connection conn = null;        
        PreparedStatement ps = null;
        
        int result = 0;
        
        try {
            conn = DBConnector.getConnection(driver, connUrl, database, user, 
                    password);
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, order.getName());
            ps.setString(2, order.getPhone());
            ps.setString(3, order.getPizzaSize());
            ps.setString(4, order.getToppings());
            ps.setBoolean(5, order.isDelivery());
            ps.setDouble(6, order.getPrice());
            
            result = ps.executeUpdate();
            
        } catch (Exception ex) {
            throw(ex);
        } finally {
            DBConnector.closeJDBCObjects(conn, ps);
        }
        
        return result;
    }
}