package com.foodorder.repository;

import com.foodorder.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Estebankito
 */

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public List<Producto> findByEstadoTrue();

}
