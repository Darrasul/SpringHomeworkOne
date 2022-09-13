package com.buzas.springdata.controllers;

import com.buzas.springdata.authorities.AuthorityDto;
import com.buzas.springdata.services.AuthorityService;
import com.buzas.springdata.services.UserService;
import com.buzas.springdata.users.User;
import com.buzas.springdata.users.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

//    http://localhost:8080/SpringData/swagger-ui/index.html

    private final UserService userService;
    private final AuthorityService authService;

    @GetMapping
    public List<String> getAllUsers() {
//        Знаю, что это крайне тяжелое по весу решение, но другого на данном этапе у меня нет: я не смог заставить
//        маппер нормально обрабатывать List, от которых формируются зависимости ManyToMany на классах.
        List<String> strings = new ArrayList<>();
        List<UserDto> users = userService.findAll();
        for (UserDto user : users) {
            strings.add(user + " with next roles: " + authService.findAllByUserId(user.getId()));
        }
        return strings;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id) {
        return userService.findById(id) + " with next roles: " + authService.findAllByUserId(id);
    }

    @GetMapping("/new")
    public ModelAndView openForm(Model model){
        model.addAttribute("user", new User());
        return new ModelAndView("NewUser");
    }

    @PostMapping ("/new")
    public ModelAndView saveNewUser(@Valid @ModelAttribute(name = "user") UserDto userDto, BindingResult result) {
        if (result.hasErrors()){
            return new ModelAndView("NewUser");
        }
        userService.save(userDto);
        return new ModelAndView("NewUser");
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        userService.deleteById(id);
    }
}
