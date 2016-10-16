/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.OrderDb;
import java.io.IOException;
import java.io.PrintWriter;
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

   @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html");
        boolean delivery=false;
        double price;
        String[] topping= request.getParameterValues("topping");
        String toppings="";
        String method = request.getParameter("method");
        String size = request.getParameter("size");
        if(topping==null)
        {
            toppings ="No topping selected";
        }
        else{
            toppings= String.join(",",request.getParameterValues("topping"));
        }
        if(method.equals("true")){
            delivery=true;
        }
        price = calculatePrice(delivery,size,toppings);
        HttpSession session = request.getSession();
        
        Customer customer = (Customer)session.getAttribute("customer");
         
        PizzaOrder order = new PizzaOrder(customer.getName(),customer.getPhone(),size,toppings,delivery,price);     
        
        // Insert Order to database
        String driver = getServletContext().getInitParameter("driver");
        String connUrl = getServletContext().getInitParameter("connUrl");
        String database = getServletContext().getInitParameter("database");
        String user = getServletContext().getInitParameter("user");
        String password = getServletContext().getInitParameter("password");
             
        OrderDb orderDb = new OrderDb(driver, connUrl, database, user, password);
        
        int result = 0;
        
        try {
            result = orderDb.createOrder(order);
        } catch (Exception ex){
            System.out.println("Order not saved");
        }
        
        
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Process Order</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+order.toString());    
            out.println("</h1>");

            //out.println("<h2>You chose:<br>Delivery Method: "+method+"<br>Pizza Size: "+size+"<br> Toppings: "+toppings+"<br>");
            /**
            out.println("<br><br>");
            out.println("<form action=\"ProcessOrder.do\" method=\"POST\">");
            out.println("<input type=\"radio\" name=\"method\" value=\"pick\">Pick Up");
            out.println("<input type=\"radio\" name=\"method\" value=\"delivery\">Delivery ($2)<br><br>");
            out.println("<h3>Select pizza size:<br>");
            out.println("<select name=\"size\">");
            out.println("<option value=\"small\">Small($5)");
            out.println("<option value=\"medium\">Medium($7)");
            out.println("<option value=\"large\" selected>Large($9)");
            out.println("</select><br><br>");
            out.println("Choose Toppings:</h3>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"pepperoni\"/>Pepperoni<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"sausage\"/>Sausage<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"spinach\"/>Baby Spinach<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"pepper\"/>Pepper<br><br>");
            out.println("<input type=\"submit\" value=\"Place my Order\"/><br><br>");
*/
            out.println("<a href=\"index.html\">Back to index</a>");
            
            out.println("</body>");
            out.println("</html>");
        }

    }

    private double calculatePrice(boolean delivery, String size, String toppings) {
        double total =0;
        
        if(delivery){
            total +=2;
        }
       switch (size) {
           case "Small":
               total +=5;
               break;
           case "Medium":
               total +=7;
               break;
           case "Large":
               total +=9;
               break;
       }
       if(!toppings.equals("No topping selected")){
       
           int toppingTotal = toppings.split(",").length;
           
           total +=toppingTotal;
       } 
        return total;
    }
}
