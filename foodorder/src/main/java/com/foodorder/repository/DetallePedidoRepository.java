package com.foodorder.repository;

import com.foodorder.domain.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Estebankito
 */

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

}