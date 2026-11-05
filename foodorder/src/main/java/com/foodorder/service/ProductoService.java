package com.foodorder.service;

import com.foodorder.domain.Producto;
import com.foodorder.repository.ProductoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activo) {
        if (activo) {
            return productoRepository.findByEstadoTrue();
        }
        return productoRepository.findAll();
    }

    @Transactional
    public void save(Producto producto) {
        if (producto.getImagen() != null && producto.getImagen().isBlank()) {
            producto.setImagen(null);
        }
        productoRepository.save(producto);
    }

    @Transactional
    public void delete(Integer idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new IllegalArgumentException("El producto con ID " + idProducto + " no existe.");
        }
        try {
            productoRepository.deleteById(idProducto);
            productoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el producto. Tiene datos asociados.", e);
        }
    }

}
