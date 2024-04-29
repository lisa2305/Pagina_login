package it.marconi.pagina_login1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/type")
public class ControllerLogin {

    //pagina new
    @GetMapping("/new")
    public ModelAndView loginForm(){
        return new ModelAndView("login-form");
    }

    //pagina login
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}   