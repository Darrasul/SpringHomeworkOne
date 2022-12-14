package com.buzas.springdata.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequestMapping("/error")
@RequiredArgsConstructor
public class ErrorController {

    @GetMapping("/denied")
    public ModelAndView getDeniedPage() {
        return new ModelAndView("Denied");
    }
}
