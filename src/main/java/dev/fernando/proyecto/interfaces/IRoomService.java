package dev.fernando.proyecto.interfaces;

import dev.fernando.proyecto.models.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService extends ICrudService<Room,Long> {
    List<Room> findActive();
    
    Optional<Room> findByIdActive(Long id);
    
    void deleteByIdActive(Long id);
}
