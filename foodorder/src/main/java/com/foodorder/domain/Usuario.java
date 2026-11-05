package com.foodorder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio.")
    @Size(max = 100, message = "El nombre no puede tener mas de 100 caracteres.")
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank(message = "El correo no puede estar vacio.")
    @Email(message = "El correo no es valido.")
    @Size(max = 100, message = "El correo no puede tener mas de 100 caracteres.")
    private String correo;

    @Column(length = 255, nullable = false)
    private String contrasena;

    @Column(length = 20, nullable = false)
    private String rol;
}