package com.foodorder.repository;

import com.foodorder.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Estebankito
 */

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}