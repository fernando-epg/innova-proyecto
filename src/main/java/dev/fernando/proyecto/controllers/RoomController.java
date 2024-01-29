package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.interfaces.IRoomService;
import dev.fernando.proyecto.models.EStatus;
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
    
    @GetMapping
    public ResponseEntity<?> findAllRoomsActive() {
        Optional<List<Room>> rooms = Optional.ofNullable(roomService.findActive());
        if (rooms.isPresent()) {
            return ResponseEntity.ok(rooms.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Room> room = roomService.findByIdActive(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Room> room = roomService.findByIdActive(id);
        if (room.isPresent()) {
            roomService.deleteByIdActive(id);
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> deleteRoomActive(@RequestBody Room entity) {
        Optional<Room> room = roomService.findByIdActive(entity.getId());
        if (room.isPresent()) {
            roomService.deleteActive(room.get());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody Room entity) {
        if(invalidEntity(entity)) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        entity.setActive(true);
        entity.setStatus(EStatus.RESERVED);
        Optional<Room> room = Optional.empty();
        if(roomService.findStatusByRoomNumber(entity.getRoomNumber()).isEmpty()) {
            room = Optional.ofNullable(roomService.save(entity));
        }
        if (room.isPresent()) {
            return ResponseEntity.ok(room);
        } else if (roomService.findStatusByRoomNumber(entity.getRoomNumber()).isPresent()) {
            return ResponseEntity.badRequest().body("Bad request");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Room newRoom, @PathVariable Long id) {
        if(invalidEntity(newRoom)) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        Optional<Room> room = roomService.findByIdActive(id);
        Optional<Room> roomStatus = roomService.findStatusByRoomNumber(newRoom.getRoomNumber());
        if (roomStatus.isEmpty() && room.isPresent()) {
            if (newRoom.getRoomNumber() != null) {
                room.get().setRoomNumber(newRoom.getRoomNumber());
            }
            if (newRoom.getName() != null) {
                room.get().setName(newRoom.getName());
            }
            if(newRoom.getNumAdults() != null) {
                room.get().setNumAdults(newRoom.getNumAdults());
            }
            if(newRoom.getNumMinors() != null) {
                room.get().setNumMinors(newRoom.getNumMinors());
            }
            if(newRoom.getDate() != null) {
                room.get().setDate(newRoom.getDate());
            }
            roomService.save(room.get());
            return ResponseEntity.ok(roomService.findByIdActive(room.get().getId()));
        } if (roomStatus.isPresent() && room.isEmpty()) {
            return ResponseEntity.badRequest().body("Bad request");
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    
    private boolean invalidEntity(Room room) {
        return room.getRoomNumber() == null || room.getName() == null ||
                room.getNumAdults() == null || room.getNumMinors() == null || room.getDate() == null;
    }
}
