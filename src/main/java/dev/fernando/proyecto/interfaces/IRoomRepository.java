package dev.fernando.proyecto.interfaces;

import dev.fernando.proyecto.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomRepository extends JpaRepository<Room, Long> {
}
