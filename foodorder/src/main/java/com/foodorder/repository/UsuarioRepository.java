package com.foodorder.repository;

import com.foodorder.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Estebankito
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}