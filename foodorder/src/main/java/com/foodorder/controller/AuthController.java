package com.foodorder.controller;

import com.foodorder.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final MessageSource messageSource;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String correo, @RequestParam String contrasena, HttpSession session, RedirectAttributes redirectAttributes) {
        var usuario = usuarioService.login(correo, contrasena);
        if (usuario.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("error.login", null, Locale.getDefault()));
            return "redirect:/login";
        }
        session.setAttribute("usuarioLogueado", usuario.get());
        return "redirect:/";
    }

    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
