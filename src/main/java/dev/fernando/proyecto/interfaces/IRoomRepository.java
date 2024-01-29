package dev.fernando.proyecto.interfaces;

import dev.fernando.proyecto.models.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.active = true")
    List<Room> findActive();
    
    @Query("SELECT r FROM Room r WHERE r.active = true AND r.id = ?1")
    Optional<Room> findByIdActive(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("UPDATE Room r SET r.active = false WHERE r.id = ?1")
    void deleteByIdActive(@Param("id") Long id);
    
    @Query("SELECT r FROM Room r WHERE r.active = true AND r.roomNumber = ?1")
    Optional<Room> findStatusByRoomNumber(int roomNumber);
    
}
