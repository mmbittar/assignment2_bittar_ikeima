/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.OrderDb;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PizzaOrder;

/**
 *
 * @author bittar_ikeima
 */
public class DisplayOrders extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        
        
        // Get parameters to access Database
        String driver = getServletContext().getInitParameter("driver");
        String connUrl = getServletContext().getInitParameter("connUrl");
        String database = getServletContext().getInitParameter("database");
        String user = getServletContext().getInitParameter("user");
        String password = getServletContext().getInitParameter("password");
             
        OrderDb orderDb = new OrderDb(driver, connUrl, database, user, password);
        
        ArrayList<PizzaOrder> order = new ArrayList();

        // Get orders
        try {
            order = orderDb.getOrder();
        } catch (Exception ex){
            System.out.println("No Orders");
        }
        
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Display Orders</title>");
            out.println("<link href=\"CSS/style.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div id=\"header\">");
            out.println("<h1>Assignment 2 - Bittar_Ikeima<h1>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div id=\"wrapper1020\">");
            out.println("<h2>Orders</h2>");
            out.println("<div class=\"row\">");
            
            // Create table for each order
            for (int i = 0; i < order.size(); i++) {
                
                out.println("<div class=\"third\">");
                out.println("<table>");
                out.println("<tr>");
                out.println("<td>Order number: </td>");
                out.println("<td>" + order.get(i).getId() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Customer: </td>");
                out.println("<td>" + order.get(i).getName() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Phone: </td>");
                out.println("<td>" + order.get(i).getPhone() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Pizza Size: </td>");
                out.println("<td>" + order.get(i).getPizzaSize() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Toppings: <br><br><br><br><br><br></td>");
                out.println("<td>");
                out.println("<ul>");
                
                // List toppings
                String toppings = order.get(i).getToppings();
                               
                for (String toppingSplit: toppings.split(",")) {
                    out.println("<li>" + toppingSplit + "</li>");
                }
                
                out.println("</td></ul>");               
                out.println("</tr>");
                out.println("<td>Delivery/Pick-up: </td>");
                
                // Get Delivery / Pick-up option
                if (order.get(i).isDelivery()){
                    out.println("<td>Delivery</td>");
                } else {
                    out.println("<td>Pick-up</td>");
                }
                
                out.println("<tr>");
                out.println("<td>Total: </td>");
                out.println("<td>" + String.format("$%.2f",order.get(i).getPrice()) + "</td>");
                out.println("</tr>");                
                out.println("</table>");
                out.println("<div class=\"button full\">");
                out.println("<a href=\"UpdateOrders.do?dismiss="+ order.get(i).getId() +"\">Dismiss order</a>");
                out.println("</div>");
                out.println("</div>");
                
                // Create another row. Max number of tables in a row is 3
                if ((i + 1) % 3 == 0){
                    out.println("</div> <!-- class=\"row\"-->");
                    out.println("<div class=\"row\"> <!-- class=\"row\"-->");
                }
            }
            out.println("</div> <!-- class=\"row\"-->");
            out.println("<a href=\"index.html\">Back to index</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
