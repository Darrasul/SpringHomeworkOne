package com.buzas;

import com.buzas.customers.Customer;
import com.buzas.customers.CustomerDao;
import com.buzas.dao.MainDao;
import com.buzas.orders.Order;
import com.buzas.orders.OrderDao;
import com.buzas.products.Product;
import com.buzas.products.ProductDao;

import java.lang.module.FindException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Инициализация дао
        MainDao mainDao = new MainDao();
        ProductDao productDao = new ProductDao();
        CustomerDao customerDao = new CustomerDao();
        OrderDao orderDao = new OrderDao();

//        Инициализация продуктов
//        Product product1 = new Product();
//        product1.setTitle("product#1");
//        product1.setPrice(100);
//        Product product2 = new Product();
//        product2.setTitle("product#2");
//        product2.setPrice(200);
//        Product product3 = new Product();
//        product3.setTitle("product#3");
//        product3.setPrice(300);

//        Инициализация покупателей
//        Customer customer1 = new Customer();
//        customer1.setName("customer1");
//        Customer customer2 = new Customer();
//        customer2.setName("customer2");
//        Customer customer3 = new Customer();
//        customer3.setName("customer3");

//        Сохранение продуктов в базу
//        productDao.saveOrUpdate(product1);
//        productDao.saveOrUpdate(product2);
//        productDao.saveOrUpdate(product3);

//        Сохранение покупателей в базу
//        customerDao.saveOrUpdate(customer1);
//        customerDao.saveOrUpdate(customer2);
//        customerDao.saveOrUpdate(customer3);

//        Инициализация заказов
//        List<Product> productList1 = Arrays.asList(
//                productDao.findById(1L).orElseThrow(() -> new FindException("Product not exist")),
//                productDao.findById(2L).orElseThrow(() -> new FindException("Product not exist"))
//        );
//        Order order1 = new Order(customerDao.findById(2L).orElseThrow(() ->
//                new FindException("Customer not exist")), productList1);
//        List<Product> productList2 = Arrays.asList(
//                productDao.findById(1L).orElseThrow(() -> new FindException("Product not exist")),
//                productDao.findById(3L).orElseThrow(() -> new FindException("Product not exist"))
//        );
//        Order order2 = new Order(customerDao.findById(3L).orElseThrow(() ->
//                new FindException("Customer not exist")), productList2);
//        orderDao.saveOrUpdate(order1);
//        orderDao.saveOrUpdate(order2);

//        Ищем все id заказов по id пользователя
        System.out.println(orderDao.findAllOrdersByCustomerId(2L));

//        Ищем все id заказов по id продукта
        for (Long id : productDao.showAllOrdersIdWithProduct(1L)) {
            System.out.println(id);
        }
//        Узнаем стоимость заказа
        System.out.println(orderDao.findTotalCostByOrderId(1L));
    }
}
