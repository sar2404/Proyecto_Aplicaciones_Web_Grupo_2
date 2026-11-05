package com.foodorder.service;

import com.foodorder.domain.Categoria;
import com.foodorder.repository.CategoriaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public void delete(Integer idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new IllegalArgumentException("La categoria con ID " + idCategoria + " no existe.");
        }
        try {
            categoriaRepository.deleteById(idCategoria);
            categoriaRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar la categoria. Tiene datos asociados.", e);
        }
    }

}
