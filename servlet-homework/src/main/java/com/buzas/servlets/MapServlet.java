package com.buzas.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// Страница чисто чтобы не заблудиться
@WebServlet(urlPatterns = "/")
public class MapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h2>To open a page with the following servlet, specify the correct path</h2>");
        response.getWriter().println("<h4>Servlet generating and displaying ten products - /showTen</h4>");
        response.getWriter().println("<h4>Servlet displaying ten static products with links - /showStaticTen</h4>");
        response.getWriter().println("<h4>Servlet displaying static product - /n where n- needed number</h4>");
    }
}
