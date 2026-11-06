package com.foodorder.controller;

import com.foodorder.domain.Usuario;
import com.foodorder.service.CarritoService;
import com.foodorder.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;
    private final PedidoService pedidoService;
    private final MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(HttpSession session, Model model) {
        var items = carritoService.getItems(session);
        model.addAttribute("items", items);
        model.addAttribute("total", carritoService.getTotal(items));
        return "carrito/listado";
    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam Integer idProducto, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            carritoService.agregar(session, idProducto);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("carrito.error01", null, Locale.getDefault()));
        }
        return "redirect:/carrito/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idProducto, HttpSession session) {
        carritoService.eliminar(session, idProducto);
        return "redirect:/carrito/listado";
    }

    @PostMapping("/vaciar")
    public String vaciar(HttpSession session) {
        carritoService.vaciar(session);
        return "redirect:/carrito/listado";
    }

    @PostMapping("/confirmar")
    public String confirmar(HttpSession session, RedirectAttributes redirectAttributes) {
        var usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/login";
        }
        try {
            var pedido = pedidoService.crearPedido(usuario.getCorreo(), carritoService.getItems(session));
            carritoService.vaciar(session);
            redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("pedido.confirmado", null, Locale.getDefault()));
            return "redirect:/pedido/detalle/" + pedido.getIdPedido();
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("pedido.error01", null, Locale.getDefault()));
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("pedido.error02", null, Locale.getDefault()));
        }
        return "redirect:/carrito/listado";
    }

}
