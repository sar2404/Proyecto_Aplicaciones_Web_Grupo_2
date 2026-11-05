package com.foodorder.repository;

import com.foodorder.domain.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Estebankito
 */

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    public List<Pedido> findAllByOrderByIdPedidoDesc();

    public List<Pedido> findByUsuarioCorreoOrderByIdPedidoDesc(String correo);

    public List<Pedido> findByEstadoNotOrderByIdPedidoAsc(String estado);

}
