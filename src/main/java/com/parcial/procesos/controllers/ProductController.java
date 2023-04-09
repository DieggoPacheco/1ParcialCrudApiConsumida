package com.parcial.procesos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping(value = "/hola")
    public String saludo(){
        return "Api Base Funcionando";
    }
}