package com.buzas.controllers;

import com.buzas.products.Product;
import com.buzas.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class BootController {

    private final ProductRepository productRepo;

    @GetMapping
    public String getMainPage() {
        log.info("User open main page");
        return "MainPage";
    }

    @GetMapping("product")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepo.showAll());
//        model.addAttribute("product", new Product("name"));
        return "ProductsPage";
    }

    @GetMapping("product/{id}")
    public String getProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepo.findById(id));
        return "ProductPage";
    }

    @PostMapping("product/create")
    public String createProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()){
            log.warn("User has errors in update form");
            return "NewProductPage";
        }
        productRepo.insert(product);
        return "ProductsPage";
    }

    @GetMapping("product/new")
    public String getNewProductForm(Model model){
        model.addAttribute("product", new Product("New product"));
        return "NewProductPage";
    }


    @PostMapping("product/update/")
    public String updateProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()){
            log.warn("User has errors in update form");
            return "ProductPage";
        }
        productRepo.update(product);
        return "ProductPage";
    }
    @PostMapping("product/delete/{id}")
    public String deleteProductPost(@PathVariable("id") long id) {
        productRepo.delete(id);
        return "ProductsPage";
    }

    @PostMapping("product/delete/all")
    public String deleteAllProductsPost() {
        productRepo.deleteAll();
        return "ProductsPage";
    }

}
