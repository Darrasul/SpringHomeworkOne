package com.buzas;


import com.buzas.products.Product;
import com.buzas.products.ProductDao;

import java.util.List;

public class Main {

    private static ProductDao dao = new ProductDao();

    public static void main(String[] args) {
//        findById(1L);
//        updateProduct(1L);
//        deleteProduct(2L);
//        addProducts();
        findAll();
    }

//    Успешно
    private static void findById(Long id) {
        System.out.println(dao.findById(id));
    }

//    Успешно
    private static void updateProduct(Long id){
        Product product = dao.findById(id);
        product.setTitle(product.getTitle() + "New");
        dao.saveOrUpdate(product);
    }

//    Успешно
    private static void deleteProduct(Long id) {
        dao.deleteById(id);
    }

//    Успешно
    private static void addProducts() {
        dao.saveOrUpdate(new Product("Product1", 100));
        dao.saveOrUpdate(new Product("Product2", 200));
        dao.saveOrUpdate(new Product("Product3", 300));
    }

//    Успешно
    private static void findAll() {
        List<Product> products = dao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
