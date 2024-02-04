package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.dto.CourseDTO;
import dev.fernando.proyecto.interfaces.ICourseGenericService;
import dev.fernando.proyecto.services.postgresql.JpaCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/course")
public class CourseController {
    private final ICourseGenericService service;
    
    @Autowired
    public CourseController(ICourseGenericService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Object id) {
        var parsedId = id;
        if(service.getClass() == JpaCourseService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<CourseDTO> course = service.findById(parsedId);
        if(course.isPresent()) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() {
        Optional<List<CourseDTO>> courses = Optional.ofNullable(service.findAll());
        if(courses.isPresent()) {
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Object id) {
        var parsedId = id;
        if(service.getClass() == JpaCourseService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<CourseDTO> course = service.findById(parsedId);
        if(course.isPresent()) {
            service.deleteById(parsedId);
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody(required = false) CourseDTO dto) {
        if(dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        var parsedId = dto.getId();
        if(service.getClass() == JpaCourseService.class) {
            parsedId = Long.valueOf(dto.getId().toString());
        }
        Optional<CourseDTO> course = service.findById(parsedId);
        if(course.isPresent()) {
            service.delete(course.get());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> add(@RequestBody(required = false) CourseDTO dto) {
        if(dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        Optional<CourseDTO> newCourse = Optional.ofNullable((CourseDTO) service.save(dto));
        if(newCourse.isPresent()) {
            return ResponseEntity.ok(newCourse);
        } else {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody(required = false) CourseDTO dto, @PathVariable Object id) {
        if(dto == null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        var parsedId = id;
        if(service.getClass() == JpaCourseService.class) {
            parsedId = Long.valueOf(id.toString());
        }
        Optional<CourseDTO> course = service.findById(parsedId);
        if(course.isPresent()) {
            if(dto.getCode()!=null) {
                course.get().setCode(dto.getCode());
            }
            if(dto.getName() != null) {
                course.get().setName(dto.getName());
            }
            if(dto.getCredits() != null) {
                course.get().setCredits(dto.getCredits());
            }
            
            return ResponseEntity.ok(service.save(course.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
