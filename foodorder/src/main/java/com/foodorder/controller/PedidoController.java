package com.foodorder.controller;

import com.foodorder.domain.Usuario;
import com.foodorder.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("pedidos", pedidoService.getPedidos());
        return "pedido/listado";
    }

    @GetMapping("/historial")
    public String historial(HttpSession session, Model model) {
        var usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("pedidos", pedidoService.getPedidosUsuario(usuario.getCorreo()));
        return "pedido/listado";
    }

    @GetMapping("/cocina")
    public String cocina(Model model) {
        model.addAttribute("pedidos", pedidoService.getPedidosCocina());
        model.addAttribute("estados", PedidoService.ESTADOS);
        return "pedido/cocina";
    }

    @GetMapping("/detalle/{idPedido}")
    public String detalle(@PathVariable Integer idPedido, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        var usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        var pedido = pedidoService.getPedido(idPedido, usuario.getCorreo(), "CLIENTE".equals(usuario.getRol()));
        if (pedido.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("pedido.error03", null, Locale.getDefault()));
            return "redirect:/";
        }
        model.addAttribute("pedido", pedido.get());
        return "pedido/detalle";
    }

    @PostMapping("/estado")
    public String estado(@RequestParam Integer idPedido, @RequestParam String estado, RedirectAttributes redirectAttributes) {
        try {
            pedidoService.actualizarEstado(idPedido, estado);
            redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("pedido.error04", null, Locale.getDefault()));
        }
        return "redirect:/pedido/cocina";
    }

}
