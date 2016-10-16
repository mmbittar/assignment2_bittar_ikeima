/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;

/**
 *
 * @author bittar_ikeima
 */
public class BeginOrder extends HttpServlet {

 @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

         HttpSession session = request.getSession();
        
         Customer customer = new Customer(name,phone);
         
        session.setAttribute("customer", customer);


        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Place your order</title>");
            out.println("<link href=\"CSS/style.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"wrapper1020\">");
            out.println("<h1>Hi "+name+"<br>"+phone);

          
            out.println("</h1>");
            out.println("<br><br>");
            out.println("<div>");
            out.println("<div id=\"pizzaOptions\" class=\"half\">");
            out.println("<form action=\"ProcessOrder.do\" method=\"POST\">");
            out.println("<input type=\"radio\" name=\"method\" value=\"false\">Pick Up");
            out.println("<input type=\"radio\" name=\"method\" value=\"true\" checked>Delivery ($2)<br><br>");
            out.println("<h3>Select pizza size: ");
            out.println("<select name=\"size\">");
            out.println("<option value=\"Small\">Small ($5)");
            out.println("<option value=\"Medium\">Medium ($7)");
            out.println("<option value=\"Large\" selected>Large ($9)");
            out.println("</select><br><br>");
            out.println("Choose Toppings:</h3>");
            out.println("<div class=\"third\">");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Pepperoni\"/>Pepperoni<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Sausage\"/>Sausage<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Spinach\"/>Baby Spinach<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Pepper\"/>Pepper<br><br>");
            out.println("</div>");
            out.println("<div class=\"third\">");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Bacon\"/>Bacon<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Salami\"/>Salami<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Mushrooms\"/>Mushrooms<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Tomatoes\"/>Tomatoes<br><br>");
            out.println("</div>");
            out.println("<div class=\"third\">");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Broccoli\"/>Broccoli<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Onion\"/>Onion<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Provolone\"/>Provolone<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Green_Olives\"/>Green Olives<br><br>");
            out.println("</div>");
            out.println("<div class=\"button half\">");
            out.println("<input type=\"submit\" value=\"Place my Order\"/><br><br>");
            out.println("</div>");
            out.println("</form>");
            
            out.println("<div class=\"button half\">");
            out.println("<a href=\"index.html\">Cancel</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div id=\"pizzaImage\" class=\"half\">");
            out.println("<img src=\"images\\base.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Provolone\" style=\"z-index: 1; display: none;\" src=\"images\\provolone.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Pepperoni\" style=\"z-index: 2; display: none;\" src=\"images\\pepperoni.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Sausage\" style=\"z-index: 3; display: none;\" src=\"images\\sausage.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Bacon\" style=\"z-index: 4; display: none;\" src=\"images\\bacon.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Salami\" style=\"z-index: 5; display: none;\" src=\"images\\salami.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Onion\" style=\"z-index: 6; display: none;\" src=\"images\\onion.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Broccoli\" style=\"z-index: 7; display: none;\" src=\"images\\broccoli.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Spinach\" style=\"z-index: 8; display: none;\" src=\"images\\baby_spinach.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Mushrooms\" style=\"z-index: 9; display: none;\" src=\"images\\mushrooms.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Tomatoes\" style=\"z-index: 10; display: none;\" src=\"images\\tomatoes.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Pepper\" style=\"z-index: 11; display: none;\" src=\"images\\pepper.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Green_Olives\" style=\"z-index: 12; display: none;\" src=\"images\\green_olives.png\" />");
            out.println("</div>");
            out.println("</div>");
            out.println("</div> <!-- id=\"wrapperOrder\"-->");
            out.println("<script type=\"text/javascript\" src=\"js/pizzaImages.js\"></script>");
            out.println("</body>");
            out.println("</html>");
        }

    }

}