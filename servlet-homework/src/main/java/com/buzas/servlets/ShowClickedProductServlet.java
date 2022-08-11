package com.buzas.servlets;

import com.buzas.items.Product;
import com.buzas.items.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/*")
public class ShowClickedProductServlet extends HttpServlet {

    private final ProductRepository repo = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestingNumber = Integer.parseInt(request.getPathInfo().substring(1));
        Product product = repo.findById(requestingNumber);
        response.getWriter().println(product);
    }

    @Override
    public void init() throws ServletException {
        for (int i = 0; i < 10; i++) {
            repo.add(new Product("Product #" + (i + 1), (i * 250)));
        }
    }
}
