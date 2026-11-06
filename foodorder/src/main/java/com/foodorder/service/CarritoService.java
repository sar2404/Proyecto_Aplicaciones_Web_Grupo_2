package com.foodorder.service;

import com.foodorder.domain.Item;
import com.foodorder.repository.ProductoRepository;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final ProductoRepository productoRepository;

    @SuppressWarnings("unchecked")
    private Map<Integer, Integer> getCarrito(HttpSession session) {
        var carrito = (Map<Integer, Integer>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new LinkedHashMap<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }

    @Transactional(readOnly = true)
    public List<Item> getItems(HttpSession session) {
        List<Item> items = new ArrayList<>();
        for (var linea : getCarrito(session).entrySet()) {
            productoRepository.findById(linea.getKey()).ifPresent(producto -> items.add(new Item(producto, linea.getValue())));
        }
        return items;
    }

    public BigDecimal getTotal(List<Item> items) {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : items) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }

    @Transactional(readOnly = true)
    public void agregar(HttpSession session, Integer idProducto) {
        var producto = productoRepository.findById(idProducto);
        if (producto.isEmpty() || !producto.get().isEstado()) {
            throw new IllegalArgumentException("El producto con ID " + idProducto + " no esta disponible.");
        }
        getCarrito(session).merge(idProducto, 1, Integer::sum);
    }

    public void eliminar(HttpSession session, Integer idProducto) {
        getCarrito(session).remove(idProducto);
    }

    public void vaciar(HttpSession session) {
        getCarrito(session).clear();
    }

}
