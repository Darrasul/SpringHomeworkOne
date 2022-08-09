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
// Т.к. мы не прикручиваем базу данных, то здесь и в ShowClickedProductServlet сделана идентичная
// статическая инициализация репозитория.
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
//            Данные сслыки по какой-то причине остаются некликабельными, т.е. ничего не делают. Открытие новых страниц
//            по ним не дает результата. При этом отдельный сервлет данные ссылки при ручном открытии отрисовывает как
//            надо. У меня есть таблица со ссылками, у меня есть сервлет, отрисовывающий страницы, но соединить это я не смог
            writer.println("<td><a href=\"localhost:8080/ServletHomework/" + product.getId().toString() + "\">"
                    + product.getId() + "</td>");
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
