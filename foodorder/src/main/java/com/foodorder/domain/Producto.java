package com.foodorder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio.")
    @Size(max = 100, message = "El nombre no puede tener mas de 100 caracteres.")
    private String nombre;

    @Column(length = 255)
    @Size(max = 255, message = "La descripcion no puede tener mas de 255 caracteres.")
    private String descripcion;

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "El precio no puede estar vacio.")
    @DecimalMin(value = "0.01", inclusive = true, message = "El precio debe ser mayor a 0.")
    private BigDecimal precio;

    @Column(length = 1024)
    @Size(max = 1024, message = "La imagen no puede tener mas de 1024 caracteres.")
    private String imagen;

    @Column(nullable = false)
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;
}