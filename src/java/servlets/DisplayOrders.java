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
 * @author Ricardo
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
        // Search Order in database
        String driver = getServletContext().getInitParameter("driver");
        String connUrl = getServletContext().getInitParameter("connUrl");
        String database = getServletContext().getInitParameter("database");
        String user = getServletContext().getInitParameter("user");
        String password = getServletContext().getInitParameter("password");
             
        OrderDb orderDb = new OrderDb(driver, connUrl, database, user, password);
        
        ArrayList<PizzaOrder> order = new ArrayList();
        
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
            out.println("</head>");
            out.println("<body>");
            out.println("<div><h1>Orders</h1></div>");

            for (PizzaOrder orderDetail : order){
                out.println("<h2>" + orderDetail.toString() + "</h2><br>");    
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
