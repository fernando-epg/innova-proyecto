package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.interfaces.IRoomService;
import dev.fernando.proyecto.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final IRoomService roomService;
    
    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> findAllRooms() {
        Optional<List<Room>> rooms = Optional.ofNullable(roomService.findAll());
        if (rooms.isPresent()) {
            return ResponseEntity.ok(rooms.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Room> room = roomService.findById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
