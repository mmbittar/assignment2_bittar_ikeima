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
import javax.servlet.http.HttpSession;
import model.Customer;
import model.PizzaOrder;
import org.apache.catalina.tribes.util.Arrays;
import org.apache.tomcat.util.codec.binary.StringUtils;

/**
 *
 * @author bittar_ikeima
 */
public class ProcessOrder extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html");
        boolean delivery = false;
        double price;
        String[] topping = request.getParameterValues("topping");
        String toppings = "";
        String method = request.getParameter("method");
        String size = request.getParameter("size");

        // Join toppings String to store at the database (comma separated String)
        if (topping == null) {
            toppings = "No topping selected";
        } else {
            toppings = String.join(",", request.getParameterValues("topping"));
        }

        // Set delivery equals true
        if (method.equals("true")) {
            delivery = true;
        }
        price = calculatePrice(delivery, size, toppings);
        HttpSession session = request.getSession();

        Customer customer = (Customer) session.getAttribute("customer");

        PizzaOrder order = new PizzaOrder(customer.getName(),
                customer.getPhone(), size, toppings, delivery, price);

        // Get parameters to access Database
        String driver = getServletContext().getInitParameter("driver");
        String connUrl = getServletContext().getInitParameter("connUrl");
        String database = getServletContext().getInitParameter("database");
        String user = getServletContext().getInitParameter("user");
        String password = getServletContext().getInitParameter("password");

        OrderDb orderDb = new OrderDb(driver, connUrl, database, user, password);

        int result = 0;

        // Store orders in Database
        try {
            result = orderDb.createOrder(order);
        } catch (Exception ex) {
            System.out.println("Order not saved");
        }

        ArrayList<PizzaOrder> orders = new ArrayList();

        // Get orders from Database
        try {
            orders = orderDb.getOrder();
        } catch (Exception ex) {
            System.out.println("No Orders");
        }

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Your Order</title>");
            out.println("<link href=\"CSS/style.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div id=\"header\">");
            out.println("<h1>Assignment 2 - Bittar_Ikeima<h1>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div id=\"wrapper1020\">");
            out.println("<h2>Your order was processed successfully!</h2>");
            out.println("<div class=\"row\">");
            out.println("<div class=\"half\">");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Order number: </td>");
            out.println("<td>" + orders.get(orders.size() - 1).getId() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Customer: </td>");
            out.println("<td>" + orders.get(orders.size() - 1).getName() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Phone: </td>");
            out.println("<td>" + orders.get(orders.size() - 1).getPhone() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Pizza Size: </td>");
            out.println("<td>" + orders.get(orders.size() - 1).getPizzaSize() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Toppings: <br><br><br><br><br><br></td>");
            out.println("<td>");
            out.println("<ul>");

            // List toppings
            String topp = orders.get(orders.size() - 1).getToppings();

            for (String toppSplit : topp.split(",")) {
                out.println("<li>" + toppSplit + "</li>");
            }
            out.println("</td></ul>");
            out.println("</tr>");
            out.println("<td>Delivery/Pick-up: </td>");

            // Get Delivery / Pick-up option
            if (orders.get(orders.size() - 1).isDelivery()) {
                out.println("<td>Delivery</td>");
            } else {
                out.println("<td>Pick-up</td>");
            }

            out.println("<tr>");
            out.println("<td>Total: </td>");
            out.println("<td>" + String.format("$%.2f", orders.get(orders.size() - 1).getPrice()) + "</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<form action=\"BeginOrder.do\" method=\"POST\">");
            out.println("<div class=\"button half\">");
            out.println("<input type=\"submit\" value=\"Place another order\"/>");
            out.println("</div>");
            out.println("</form>");
            out.println("<div class=\"button half\">");
            out.println("<a href=\"index.html\">Exit</a>");
            out.println("</div>");
            out.println("</div><!-- class=\"half\" -->");
            out.println("<div id=\"pizzaImage\" class=\"half\">");
            out.println("<img src=\"images\\base.png\" />");

            // Create pizza image
            if (topp.contains("Pepperoni")) {
                out.println("<img class=\"toppingImage\" id=\"Pepperoni\" style=\"z-index: 1;\" src=\"images\\pepperoni.png\" />");
            }

            if (topp.contains("Sausage")) {
                out.println("<img class=\"toppingImage\" id=\"Sausage\" style=\"z-index: 2;\" src=\"images\\sausage.png\" />");
            }

            if (topp.contains("Spinach")) {
                out.println("<img class=\"toppingImage\" id=\"Spinach\" style=\"z-index: 3;\" src=\"images\\baby_spinach.png\" />");
            }

            if (topp.contains("Pepper")) {
                out.println("<img class=\"toppingImage\" id=\"Pepper\" style=\"z-index: 4;\" src=\"images\\pepper.png\" />");
            }

            out.println("</div><!-- class=\"half\" -->");
            out.println("</div><!-- class=\"row\" -->");
            out.println("</div><!--id=\"wrapper1020\"-->");
            out.println("</body>");
            out.println("</html>");
        }

    }

    /**
     * Calculate pizza price
     *
     * @param delivery True if pizza will be delivered. False if customer will
     * pick-up
     * @param size Pizza size
     * @param topping Pizza toppings
     *
     * @return Pizza price
     */
    private double calculatePrice(boolean delivery, String size, String toppings) {
        double total = 0;

        // Delivery price
        if (delivery) {
            total += 2;
        }

        // Pizza size price
        switch (size) {
            case "Small":
                total += 5;
                break;
            case "Medium":
                total += 7;
                break;
            case "Large":
                total += 9;
                break;
        }

        // Pizza topping price
        if (!toppings.equals("No topping selected")) {

            int toppingTotal = toppings.split(",").length;

            total += toppingTotal;
        }
        return total;
    }
}
