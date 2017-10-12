/*
 * SimpleForm.java
 * 
 * Created on Oct 27, 2007, 1:44:16 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rbevans;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author evansrb1
 */
public class FormServlet extends HttpServlet {
   
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SimpleForm Headers</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<b>Request Method:</b> " + request.getMethod() + "<br>");
            out.println("<b>Request URI:</b> " + request.getRequestURI() + "<br>");            
            out.println("<b>Request Protocol:</b> " + request.getProtocol() + "<br>");                        
            out.println("<table border=\"1\" align=\"CENTER\">");
            out.println("<tr bgcolor=\"#FFAD00\">");
            out.println("<th>Header Name</th><th>Header Value</th>");
            out.println("</tr>");
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = (String)headerNames.nextElement();
                out.println("<tr><td>" + headerName + "</td>");
                out.println("    <td>" + request.getHeader(headerName) + "</td></tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
