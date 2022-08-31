package com.buzas.springdata.controllers;

import com.buzas.springdata.products.Product;
import com.buzas.springdata.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.lang.module.FindException;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class BootController {

    private final ProductRepository productRepo;
//    Использовал для инициализации базы
//    @PostConstruct
//    public void init() {
//        productRepo.saveAndFlush(new Product("Product #1", 100.0, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #2", 200.0, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #3", 255.25, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #4", 400.15, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #5", 15.0, "dollars"));
//    }

    @GetMapping
    public ModelAndView getMainPage() {
        log.info("User open main page");
        return new ModelAndView("MainPage");
    }

    @GetMapping("product")
    public ModelAndView getAllProducts(@RequestParam(required = false) Optional<Double> minimumFilter,
                                       @RequestParam(required = false) Optional<Double> maximumFilter,
                                       Model model) {
        if (minimumFilter.isEmpty() && maximumFilter.isEmpty()){
            model.addAttribute("products", productRepo.findAll());
        } else if (minimumFilter.isEmpty()) {
            model.addAttribute("products", productRepo.findAllByPriceLessThanEqual(maximumFilter.get()));
        } else if (maximumFilter.isEmpty()) {
            model.addAttribute("products", productRepo.findAllByPriceGreaterThanEqual(minimumFilter.get()));
        } else {
            model.addAttribute("products",
                    productRepo.findAllByPriceLessThanEqualAndPriceGreaterThanEqual(maximumFilter.get(), minimumFilter.get()));
        }
        return new ModelAndView("ProductsPage");
    }

    @GetMapping("product/{id}")
    public ModelAndView getProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepo.findById(id).orElseThrow(() -> new FindException("No such product")));
        return new ModelAndView("ProductPage");
    }

    @PostMapping("product/create")
    public ModelAndView createProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()){
            log.warn("User has errors in update form");
            return new ModelAndView("NewProductPage");
        }
        productRepo.saveAndFlush(product);
        return new ModelAndView("ProductsPage");
    }

    @GetMapping("product/new")
    public ModelAndView getNewProductForm(Model model){
        model.addAttribute("product", new Product("New product"));
        return new ModelAndView("NewProductPage");
    }


    @PostMapping("product/update/")
    public ModelAndView updateProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()){
            log.warn("User has errors in update form");
            return new ModelAndView("ProductPage");
        }
        productRepo.saveAndFlush(product);
        return new ModelAndView("ProductPage");
    }
    @PostMapping("product/delete/{id}")
    public ModelAndView deleteProductPost(@PathVariable("id") long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new FindException("There is no such product"));
        productRepo.delete(product);
        return new ModelAndView("ProductsPage");
    }

    @PostMapping("product/delete/all")
    public ModelAndView deleteAllProductsPost() {
        productRepo.deleteAll();
        return new ModelAndView("ProductsPage");
    }

}
