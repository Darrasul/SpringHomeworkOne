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

@WebServlet(urlPatterns = "/showTen")
public class ShowTenServlet extends HttpServlet {

    private ProductRepository repo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        fillingRepo();

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
            writer.println("<td>" + product.getId() + "</td>");
            writer.println("<td>" + product.getTitle() + "</td>");
            writer.println("<td>" + product.getCost() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.printf("</body></html>");
        writer.close();
    }

    private void fillingRepo() {
        repo = new ProductRepository();
        for (int i = 0; i < 10; i++) {
            if (i != 6){
                int cost = (int) (Math.random() * 250);
                repo.add(new Product("Product #" + (i + 1), cost));
            } else {
                int cost = (int) (Math.random() * 7);
                repo.add(new Product("Product #7", cost + " dollars"));
            }
        }
    }
}
