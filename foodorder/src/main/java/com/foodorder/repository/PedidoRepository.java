package com.foodorder.repository;

import com.foodorder.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Estebankito
 */

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}