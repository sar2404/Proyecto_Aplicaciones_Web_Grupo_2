package com.foodorder.service;

import com.foodorder.domain.Usuario;
import com.foodorder.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public static final List<String> ROLES = List.of("CLIENTE", "ADMINISTRADOR", "COCINA");

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> login(String correo, String contrasena) {
        return usuarioRepository.findByCorreo(correo).filter(usuario -> passwordEncoder.matches(contrasena, usuario.getContrasena()));
    }

    // El registro publico siempre crea clientes
    @Transactional
    public void registrar(Usuario usuario) {
        usuario.setIdUsuario(null);
        usuario.setRol(ROLES.get(0));
        save(usuario);
    }

    @Transactional
    public void save(Usuario usuario) {
        if (!ROLES.contains(usuario.getRol())) {
            throw new IllegalArgumentException("El rol " + usuario.getRol() + " no es valido.");
        }
        var existente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existente.isPresent() && !existente.get().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new IllegalStateException("El correo " + usuario.getCorreo() + " ya esta registrado.");
        }
        String contrasena = usuario.getContrasena();
        if (usuario.getIdUsuario() != null && (contrasena == null || contrasena.isBlank())) {
            // Sin contrasena nueva se conserva la actual
            usuario.setContrasena(usuarioRepository.findById(usuario.getIdUsuario()).orElseThrow(() -> new IllegalArgumentException("El usuario no existe.")).getContrasena());
        } else if (contrasena == null || contrasena.length() < 8) {
            throw new IllegalArgumentException("La contrasena debe tener al menos 8 caracteres.");
        } else {
            usuario.setContrasena(passwordEncoder.encode(contrasena));
        }
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Integer idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe.");
        }
        try {
            usuarioRepository.deleteById(idUsuario);
            usuarioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el usuario. Tiene datos asociados.", e);
        }
    }

}
