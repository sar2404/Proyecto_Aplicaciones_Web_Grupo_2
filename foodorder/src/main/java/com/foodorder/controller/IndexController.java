package com.foodorder.controller;

import com.foodorder.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Estebankito
 */

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ProductoService productoService;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("productos", productoService.getProductos(true));
        return "index";
    }

}
