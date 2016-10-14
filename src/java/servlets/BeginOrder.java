/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marcelo
 */
@WebServlet(name = "BeginOrder", urlPatterns = {"/BeginOrder.do"})
public class BeginOrder extends HttpServlet {

 @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        


        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Place your order</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hi "+name+"<br>"+phone);

          
            out.println("</h1>");
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

            out.println("<a href=\"index.html\">Back to index</a>");
            
            out.println("</body>");
            out.println("</html>");
        }

    }

}