package com.buzas.servlets;

import com.buzas.items.Product;
import com.buzas.items.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/showStaticTen")
public class ShowStaticTenServlet extends HttpServlet {
    private final ProductRepository repo = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        writer.printf("<html><body>");
        writer.println("<table border=\"1\" align=\"center\">");

        writer.println("<tr>");
        writer.println("<th> id </th>");
        writer.println("<th> title </th>");
        writer.println("<th> cost </th>");
        writer.println("</tr>");

        for (Product product : repo.showAll()) {
            writer.println("<tr>");
            writer.println("<td><a href='" + getServletContext().getContextPath() +
                    "/" + product.getId().toString() + "'\">" + product.getId() + "</td>");
            writer.println("<td>" + product.getTitle() + "</td>");
            writer.println("<td>" + product.getCost() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.printf("</body></html>");
        writer.close();
    }

    @Override
    public void init() throws ServletException {
        for (int i = 0; i < 10; i++) {
            repo.add(new Product("Product #" + (i + 1), (i * 250)));
        }
    }
}
