package dev.fernando.proyecto.services;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T,Object> {
    T save (T entity);
    Optional<T> findById(Object id);
    List<T> findAll();
    void deleteById(Object id);
    void delete(T entity);
}
