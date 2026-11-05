package com.foodorder.controller;

import com.foodorder.domain.Usuario;
import com.foodorder.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registro")
public class RegistroController {

    private final UsuarioService usuarioService;
    private final MessageSource messageSource;

    @GetMapping("/nuevo")
    public String nuevo() {
        return "registro/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String mensaje = "usuario.error04";
        if (!bindingResult.hasErrors()) {
            try {
                usuarioService.registrar(usuario);
                redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("registro.exito", null, Locale.getDefault()));
                return "redirect:/login";
            } catch (IllegalArgumentException e) {
                mensaje = "usuario.error06";
            } catch (IllegalStateException e) {
                mensaje = "usuario.error05";
            }
        }
        redirectAttributes.addFlashAttribute("error", messageSource.getMessage(mensaje, null, Locale.getDefault()));
        return "redirect:/registro/nuevo";
    }

}
