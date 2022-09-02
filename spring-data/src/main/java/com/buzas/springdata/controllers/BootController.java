package com.buzas.springdata.controllers;

import com.buzas.springdata.products.ProductDto;
import com.buzas.springdata.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class BootController {

    private final ProductService productService;
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
                                       @RequestParam(required = false, defaultValue = "1") Optional<Integer> page,
                                       @RequestParam(required = false, defaultValue = "10") Optional<Integer> size,
                                       Model model) {
        int currentPage = page.orElse(1) - 1;
        int sizeValue = size.orElse(10);
        Page<ProductDto> dtoPage = productService.findAllByFilters(minimumFilter, maximumFilter, currentPage, sizeValue);
        model.addAttribute("products", dtoPage);
        model.addAttribute("lastPage", dtoPage.getTotalPages());
        model.addAttribute("firstPage", 1);
        model.addAttribute("currentPage", (currentPage + 1));
        return new ModelAndView("ProductsPage");
    }

    @GetMapping("product/{id}")
    public ModelAndView getProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow(() -> new FindException("No such product")));
        return new ModelAndView("ProductPage");
    }

    @PostMapping("product/create")
    public ModelAndView createProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result) {
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
        productService.save(productDto);
        return new ModelAndView("ProductPage");
    }

    @GetMapping("product/new")
    public ModelAndView getNewProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return new ModelAndView("NewProductPage");
    }


    @PostMapping("product/update/")
    public ModelAndView updateProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result) {
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
        productService.save(productDto);
        return new ModelAndView("ProductPage");
    }

    @PostMapping("product/delete/{id}")
    public ModelAndView deleteProductPost(@PathVariable("id") long id) {
        productService.deleteById(id);
        return new ModelAndView("ProductsPage");
    }

    @PostMapping("product/delete/all")
    public ModelAndView deleteAllProductsPost() {
        productService.deleteAll();
        return new ModelAndView("ProductsPage");
    }

}
