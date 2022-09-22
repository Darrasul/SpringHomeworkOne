package com.buzas.springdata.controllers;

import com.buzas.springdata.orders.LineItem;
import com.buzas.springdata.services.AuthorityService;
import com.buzas.springdata.services.LNService;
import com.buzas.springdata.services.OrderService;
import com.buzas.springdata.services.UserService;
import com.buzas.springdata.users.User;
import com.buzas.springdata.users.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

//    http://localhost:8080/SpringData/swagger-ui/index.html

    private final UserService userService;
    private final AuthorityService authService;
    private final OrderService orderService;
    private final LNService lnService;

    @GetMapping
    public ModelAndView getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return new ModelAndView("UsersPage");
    }

    @GetMapping("/{id}")
    public ModelAndView getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", authService.findAll());
        model.addAttribute("orders", orderService.showAllByUserId(id));
        return new ModelAndView("UpdUser");
    }
    @GetMapping("/order/{id}")
    public ModelAndView getAllProductsFromOrderByOrderId(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", lnService.showAllByOrderId(id));
        model.addAttribute("orderId", id);
        return new ModelAndView("OrderPage");
    }
    @PostMapping("/order/delete")
    public ModelAndView deleteItemFromOrder(@RequestParam long itemId, @RequestParam long orderId) {
        orderService.removeFromOrder(lnService.findById(itemId), orderId);
        return new ModelAndView("OrderPage");
    }

    @PostMapping("/order/")
    public void addItemToOrder(@RequestParam long id,
                               @RequestParam long itemId) {
        orderService.addToOrder(lnService.findById(itemId), id);
    }

    @Secured("ROLE_MainAdmin")
    @GetMapping("/new")
    public ModelAndView openNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", authService.findAll());
        return new ModelAndView("NewUser");
    }

    @Secured("ROLE_MainAdmin")
    @PostMapping ("/new")
    public ModelAndView saveNewUser(@Valid @ModelAttribute(name = "user") UserDto userDto, BindingResult result, Model model) {
        model.addAttribute("roles", authService.findAll());
        if (result.hasErrors() ||
                userDto.getPassword().isEmpty() || userDto.getUsername().isEmpty()){
            checkErrors(userDto, result);
            return new ModelAndView("NewUser");
        }
        userService.save(userDto);
        return new ModelAndView("NewUser");
    }
    @Secured("ROLE_MainAdmin")
    @PostMapping ("/update")
    public ModelAndView updateUser(@Valid @ModelAttribute(name = "user") UserDto userDto, BindingResult result, Model model) {
        model.addAttribute("roles", authService.findAll());
        if (result.hasErrors() ||
                userDto.getPassword().isEmpty() || userDto.getUsername().isEmpty()){
            checkErrors(userDto, result);
            return new ModelAndView("UpdUser");
        }
        userService.update(userDto);
        return new ModelAndView("UpdPage");
    }

    @Secured("ROLE_MainAdmin")
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        userService.deleteById(id);
    }

    @Secured("ROLE_MainAdmin")
    @PostMapping("/delete/{id}")
    public ModelAndView deleteUserByIdPost(@PathVariable("id") long id, Model model){
        userService.deleteById(id);
        model.addAttribute("users", userService.findAll());
        return new ModelAndView("UsersPage");
    }

    private void checkErrors(UserDto userDto, BindingResult result) {
        if (userDto.getPassword().isEmpty()) {
            result.addError(new FieldError(userDto.getClass().toString(), "password", "Can not be empty"));
        }
        if (userDto.getUsername().isEmpty()){
            result.addError(new FieldError(userDto.getClass().toString(), "username", "Can not be empty"));
        }
    }
}
