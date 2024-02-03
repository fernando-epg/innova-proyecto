package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.fernando.proyecto.dto.StudentDTO;

import java.util.Optional;

@RestController
@RequestMapping("v1/api/student")
public class StudentController {
    
    private final ICrudService studentService;
    
    @Autowired
    public StudentController(ICrudService studentService) {
        this.studentService = studentService;
    }
    
    @RequestMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Object id) {
        Optional<StudentDTO>student = studentService.findById(id);
    }
}
