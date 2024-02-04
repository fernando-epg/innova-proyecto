package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.dto.EnrolDTO;
import dev.fernando.proyecto.dto.StudentDTO;
import dev.fernando.proyecto.interfaces.IEnrolGenericService;
import dev.fernando.proyecto.services.postgresql.JpaEnrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/enrol")
public class EnrolController {
    
    private final IEnrolGenericService service;
    
    @Autowired
    public EnrolController(IEnrolGenericService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Object id) {
        var parsedId = id;
        if(service.getClass() == JpaEnrolService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<EnrolDTO> enrol = service.findById(parsedId);
        if(enrol.isPresent()) {
            return ResponseEntity.ok(enrol);
        } else {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() {
        Optional<List<EnrolDTO>> enrols = Optional.ofNullable(service.findAll());
        if(enrols.isPresent()) {
            return ResponseEntity.ok(enrols);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Object id) {
        var parsedId = id;
        if(service.getClass() == JpaEnrolService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<EnrolDTO> enrol = service.findById(parsedId);
        if(enrol.isPresent()) {
            service.deleteById(parsedId);
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody(required = false) EnrolDTO dto) {
        if(dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        var parsedId = dto.getId();
        if(service.getClass() == JpaEnrolService.class) {
            parsedId = Long.valueOf(dto.getId().toString());
        }
        Optional<EnrolDTO> enrol = service.findById(parsedId);
        if(enrol.isPresent()) {
            service.delete(enrol.get());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> add(@RequestBody(required = false) EnrolDTO dto) {
        if(dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        Optional<EnrolDTO> newEnrol = Optional.ofNullable((EnrolDTO) service.save(dto));
        if(newEnrol.isPresent()) {
            return ResponseEntity.ok(newEnrol);
        } else {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody(required = false) EnrolDTO dto, @PathVariable Object id) {
        if(dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        
        var parsedId = id;
        if(service.getClass() == JpaEnrolService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<EnrolDTO> enrol = service.findById(parsedId);
        if(enrol.isPresent()) {
            if(dto.getCourseId() != null) {
                enrol.get().setCourseId(dto.getCourseId());
            }
            if(dto.getStudentId() != null) {
                enrol.get().setStudentId(dto.getStudentId());
            }
            if(dto.getActive() != null) {
                enrol.get().setActive(dto.getActive());
            }
            if(dto.getSuccessfulCourse()!=null) {
                enrol.get().setSuccessfulCourse(dto.getSuccessfulCourse());
            }
            
            return ResponseEntity.ok(service.save(enrol.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
