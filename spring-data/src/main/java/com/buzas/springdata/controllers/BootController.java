package com.buzas.springdata.controllers;

import com.buzas.springdata.products.Product;
import com.buzas.springdata.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.module.FindException;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class BootController {

    private final ProductRepository productRepo;
//    Использовал для инициализации базы
//    @PostConstruct
//    public void init() {
//        productRepo.saveAndFlush(new Product("Product #6", 113.0, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #7", 250.0, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #8", 275.75, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #9", 120.0, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #10", 37.0, "dollars"));
//        productRepo.saveAndFlush(new Product("Product #11", 37.5, "dollars"));
//        productRepo.saveAndFlush(new Product("Product #12", 240.0, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #13", 230.0, "rubles"));
//        productRepo.saveAndFlush(new Product("Product #14", 50.0, "rubles"));
//    }

    @GetMapping
    public ModelAndView getMainPage() {
        log.info("User open main page");
        return new ModelAndView("MainPage");
    }

    @GetMapping("product")
    public ModelAndView getAllProducts(@RequestParam(required = false) Double minimumFilter,
                                       @RequestParam(required = false) Double maximumFilter,
                                       Model model) {
        model.addAttribute("products", productRepo.findAllByFilters(maximumFilter, minimumFilter));
        return new ModelAndView("ProductsPage");
    }

    @GetMapping("product/{id}")
    public ModelAndView getProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepo.findById(id).orElseThrow(() -> new FindException("No such product")));
        return new ModelAndView("ProductPage");
    }

    @PostMapping("product/create")
    public ModelAndView createProduct(@Valid Product product, BindingResult result) {
//        Нахожусь в ветке 7 урока, где обнаружил ошибку: по какой-то причине не работает валидация.
//        Причем не работает от слова совсем: result присылает полностью пустой вариант без единой
//        ошибки даже когда все поля пустые.
//        Перепробовал все варианты с валидацией на стороне Product, так же попытался отлавливать здесь через
//        result.hasGlobalErrors(). Проверил заполнение на стороне html страниц, попробовал добавить
//        @ModelAttribute("product") для product. Даже попробовал вариант с Model model и дальнейшим
//        model.addAttribute("product", product);
//        ни один из вариантов не помог решить проблему на данном этапе приступаю к выполнению задания 8'го урока
//        после выполнения д\з буду пытаться починить валидацию: решение проблемы уже будет находиться в ветке следующего урока
        System.out.println(result.getTarget());
        System.out.println(result.hasErrors());
        System.out.println(result.getGlobalErrorCount());
        System.out.println(result.getModel());

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " has next error on " + error.getField() + " :"
                        + error.getDefaultMessage());
                log.warn("User" + error.getObjectName() + " has errors in create form with "  + error.getField() + " :"
                        + error.getDefaultMessage());
            }
            return new ModelAndView("ProductPage");
        }
        productRepo.saveAndFlush(product);
        return new ModelAndView("ProductPage");
    }

    @GetMapping("product/new")
    public ModelAndView getNewProductForm(Model model) {
        model.addAttribute("product", new Product());
        return new ModelAndView("NewProductPage");
    }


    @PostMapping("product/update/")
    public ModelAndView updateProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " has next error on " + error.getField() + " :"
                + error.getDefaultMessage());
                log.warn("User" + error.getObjectName() + " has errors in update form with "  + error.getField() + " :"
                        + error.getDefaultMessage());
            }
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
