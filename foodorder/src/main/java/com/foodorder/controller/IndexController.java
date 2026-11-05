package com.foodorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Estebankito
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }
}