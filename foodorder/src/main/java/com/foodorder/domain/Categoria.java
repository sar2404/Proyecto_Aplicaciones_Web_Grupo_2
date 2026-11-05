package com.foodorder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Estebankito
 */


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio.")
    @Size(max = 50, message = "El nombre no puede tener mas de 50 caracteres.")
    private String nombre;

    @Column(length = 255)
    @Size(max = 255, message = "La descripcion no puede tener mas de 255 caracteres.")
    private String descripcion;
}