package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.interfaces.IRoomService;
import dev.fernando.proyecto.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final IRoomService roomService;
    
    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }
    
    /*@GetMapping("/all")
    public ResponseEntity<?> findAllRooms() {
        Optional<List<Room>> rooms = Optional.ofNullable(roomService.findAll());
        if (rooms.isPresent()) {
            return ResponseEntity.ok(rooms.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
    @GetMapping
    public ResponseEntity<?> findActive() {
        Optional<List<Room>> rooms = Optional.ofNullable(roomService.findActive());
        if(rooms.isPresent()) {
            return ResponseEntity.ok(rooms.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /*@GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Room> room = roomService.findById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Room> room = roomService.findByIdActive(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /*@DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Room> room = roomService.findById(id);
        if(room.isPresent()) {
            roomService.deleteById(id);
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Room> room = roomService.findByIdActive(id);
        if(room.isPresent()) {
            roomService.deleteByIdActive(id);
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody Room entity) {
        entity.setActive(true);
        entity.setBooked(true);
        Optional<Room> room = Optional.ofNullable(roomService.save(entity));
        if(room.isPresent()) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
