package com.buzas.controllers;

import com.buzas.products.Product;
import com.buzas.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

//    Перед построением связи между контроллером и html-страницами использовал Postman для просмотра результатов запросов
//    данные запросы подписаны поверх своего функционала

    private final ProductRepository productRepo;

//    http://localhost:8080/mvc-homework/
    @GetMapping
    public String getMainPage() {
        return "MainPage";
    }

//    http://localhost:8080/mvc-homework/product
    @GetMapping("product")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepo.showAll());
        return "ProductsPage";
    }

//    http://localhost:8080/mvc-homework/product/*
    @GetMapping("product/{id}")
    public String getProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepo.findById(id));
        return "ProductPage";
    }

//    http://localhost:8080/mvc-homework/product?name=product X - добавит продукт в конце списка.
//    http://localhost:8080/mvc-homework/product?name=product Z&id=3 - если удалена 3яя позиция, поставит туда товар, но
//                                                                     не будет отображать id - скорее всего проблема в Map'е
//    http://localhost:8080/mvc-homework/product?name=product C&id=7 - опять таки проблема с отображением id. Поиск по ID
//                                                                     выдает данные продукты, но без ID
//    В любом случае сделал данный функционал лишь чтобы посмотреть, как это будет отрабатывать на практике и результат увидел
//    @PostMapping("/product")
//    @ResponseBody
//    public void createProduct(@RequestParam(required = false)Optional<Long> id, @RequestParam String name) {
//        if (id.isEmpty()){
//            productRepo.insert(new Product(name));
//        } else {
//            productRepo.insert(new Product(name), id.get());
//        }
//    }

    @PostMapping("product/create")
    public String createProduct(Product product) {
        productRepo.insert(product);
//        По какой-то причине, несмотря на одинаковый код здесь, в update и deleteAll,
//        в тех методах оно возвращает нужные страницы, а здесь оставляет лишь оболочку
//        от таблицы
        return "ProductsPage";
    }


    @PostMapping("product/update/")
    public String updateProduct(Product product) {
        productRepo.update(product);
        return "ProductPage";
    }

//   !!! Так как html не поддерживает запросы отличные от post и get, delete запросы представленные первоначально и
//    отработанные в Postman останутся тут только для показа, что они отработаны. Данные запросы переработаны в Post
//    запросы сразу после своих "нормальных" вариантов

//    http://localhost:8080/mvc-homework/product/*
    @DeleteMapping("product/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable("id") long id) {
        productRepo.delete(id);
    }

//    http://localhost:8080/mvc-homework/product/all
    @DeleteMapping("product/all")
    @ResponseBody
    public void deleteProduct() {
        productRepo.deleteAll();
    }

//    http://localhost:8080/mvc-homework/product/delete/*
    @PostMapping("product/delete/{id}")
    public String deleteProductPost(@PathVariable("id") long id) {
        productRepo.delete(id);
        return "ProductsPage";
    }

//    http://localhost:8080/mvc-homework/product/delete/all
    @PostMapping("product/delete/all")
    public String deleteAllProductsPost() {
        productRepo.deleteAll();
        return "ProductsPage";
    }

}
