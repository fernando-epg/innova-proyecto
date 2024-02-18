package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.interfaces.IStudentGenericService;
import dev.fernando.proyecto.services.postgresql.JpaStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.fernando.proyecto.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    
    private final IStudentGenericService service;
    
    @Autowired
    public StudentController(IStudentGenericService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Object id) {
        var parsedId = id;
        if (service.getClass() == JpaStudentService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<StudentDTO> student = service.findById(parsedId);
        if (student.isPresent()) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() {
        Optional<List<StudentDTO>> students = Optional.ofNullable(service.findAll());
        if (students.isPresent()) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Object id) {
        var parsedId = id;
        if (service.getClass() == JpaStudentService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<StudentDTO> student = service.findById(parsedId);
        if (student.isPresent()) {
            service.deleteById(parsedId);
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody(required = false) StudentDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        var parsedId = dto.getId();
        if (service.getClass() == JpaStudentService.class) {
            parsedId = Long.valueOf(dto.getId().toString());
        }
        Optional<StudentDTO> student = service.findById(parsedId);
        if (student.isPresent()) {
            service.delete(student.get());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> add(@RequestBody(required = false) StudentDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        Optional<StudentDTO> newStudent = Optional.ofNullable((StudentDTO) service.save(dto));
        if (newStudent.isPresent()) {
            return ResponseEntity.ok(newStudent);
        } else {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody(required = false) StudentDTO dto, @PathVariable Object id) {
        if (dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        
        var parsedId = id;
        if (service.getClass() == JpaStudentService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<StudentDTO> student = service.findById(parsedId);
        if (student.isPresent()) {
            if (dto.getFirstName() != null) {
                student.get().setFirstName(dto.getFirstName());
            }
            if (dto.getLastName() != null) {
                student.get().setLastName(dto.getLastName());
            }
            if (dto.getDob() != null) {
                student.get().setDob(dto.getDob());
            }
            if (dto.getGender() != null) {
                student.get().setGender(dto.getGender());
            }
            if (dto.getPersonalEmail() != null) {
                student.get().setPersonalEmail(dto.getPersonalEmail());
            }
            if (dto.getContactPhone() != null) {
                student.get().setContactPhone(dto.getContactPhone());
            }
            
            return ResponseEntity.ok(service.save(student.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
}
