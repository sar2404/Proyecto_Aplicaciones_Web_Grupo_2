package com.foodorder.service;

import com.foodorder.domain.DetallePedido;
import com.foodorder.domain.Item;
import com.foodorder.domain.Pedido;
import com.foodorder.repository.DetallePedidoRepository;
import com.foodorder.repository.PedidoRepository;
import com.foodorder.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    public static final List<String> ESTADOS = List.of("Pendiente", "En preparacion", "Listo", "Entregado");

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Pedido> getPedidos() {
        return pedidoRepository.findAllByOrderByIdPedidoDesc();
    }

    @Transactional(readOnly = true)
    public List<Pedido> getPedidosUsuario(String correo) {
        return pedidoRepository.findByUsuarioCorreoOrderByIdPedidoDesc(correo);
    }

    @Transactional(readOnly = true)
    public List<Pedido> getPedidosCocina() {
        return pedidoRepository.findByEstadoNotOrderByIdPedidoAsc("Entregado");
    }

    // Un cliente solo puede consultar sus propios pedidos
    @Transactional(readOnly = true)
    public Optional<Pedido> getPedido(Integer idPedido, String correo, boolean esCliente) {
        return pedidoRepository.findById(idPedido).filter(pedido -> !esCliente || pedido.getUsuario().getCorreo().equals(correo));
    }

    @Transactional
    public Pedido crearPedido(String correo, List<Item> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos un producto.");
        }
        var usuario = usuarioRepository.findByCorreo(correo).orElseThrow(() -> new IllegalArgumentException("El usuario " + correo + " no existe."));
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : items) {
            if (!item.getProducto().isEstado()) {
                throw new IllegalStateException("El producto " + item.getProducto().getNombre() + " no esta disponible.");
            }
            total = total.add(item.getSubtotal());
        }
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(total);
        pedido.setEstado(ESTADOS.get(0));
        pedido = pedidoRepository.save(pedido);
        for (Item item : items) {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(item.getProducto());
            detalle.setCantidad(item.getCantidad());
            detalle.setSubtotal(item.getSubtotal());
            detallePedidoRepository.save(detalle);
        }
        return pedido;
    }

    @Transactional
    public void actualizarEstado(Integer idPedido, String estado) {
        if (!ESTADOS.contains(estado)) {
            throw new IllegalArgumentException("El estado " + estado + " no es valido.");
        }
        var pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new IllegalArgumentException("El pedido con ID " + idPedido + " no existe."));
        pedido.setEstado(estado);
    }

}
