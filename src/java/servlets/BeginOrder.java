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

        // Create Session
        HttpSession session = request.getSession();
        Customer customer = null;
        String name = null;
        String phone = null;

        // Get customer name from request if not null. Get customer name from
        // session otherwise (Used to place another order
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
            phone = request.getParameter("phone");

            customer = new Customer(name, phone);
            session.setAttribute("customer", customer);
        } else {
            name = ((Customer) session.getAttribute("customer")).getName();
            phone = ((Customer) session.getAttribute("customer")).getPhone();
        }
        //Prints HTML page with pizza options for customer to choose
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Place your order</title>");
            out.println("<link href=\"CSS/style.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<div id=\"header\">");
            out.println("<h1>Assignment 2 - Bittar_Ikeima<h1>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div id=\"wrapper1020\">");
            out.println("<h2>Hi " + name + "<br>" + phone);
            out.println("</h2>");
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
            out.println("</select><br><br><br>");
            out.println("Choose Toppings:</h3>");
            out.println("<div id=\"toppings\" class=\"full\">");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Pepperoni\"/>Pepperoni<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Sausage\"/>Sausage<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Spinach\"/>Baby Spinach<br>");
            out.println("<input type=\"checkbox\" name=\"topping\" value=\"Pepper\"/>Pepper<br><br><br><br>");
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
            out.println("<img class=\"toppingImage\" id=\"Pepperoni\" style=\"z-index: 1; display: none;\" src=\"images\\pepperoni.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Sausage\" style=\"z-index: 2; display: none;\" src=\"images\\sausage.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Spinach\" style=\"z-index: 3; display: none;\" src=\"images\\baby_spinach.png\" />");
            out.println("<img class=\"toppingImage\" id=\"Pepper\" style=\"z-index: 4; display: none;\" src=\"images\\pepper.png\" />");
            out.println("</div>");
            out.println("</div>");
            out.println("</div> <!-- id=\"wrapperOrder\"-->");
            out.println("<script type=\"text/javascript\" src=\"js/pizzaImages.js\"></script>");
            out.println("</body>");
            out.println("</html>");
        }

    }

}
