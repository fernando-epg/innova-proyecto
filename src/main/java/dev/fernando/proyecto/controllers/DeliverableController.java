package dev.fernando.proyecto.controllers;

import dev.fernando.proyecto.dto.DeliverableDTO;
import dev.fernando.proyecto.interfaces.IDeliverableGenericService;
import dev.fernando.proyecto.services.postgresql.JpaDeliverableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/deliverable")
public class DeliverableController {
    
    private final IDeliverableGenericService service;
    
    @Autowired
    public DeliverableController(IDeliverableGenericService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Object id) {
        var parseId = id;
        if (service.getClass() == JpaDeliverableService.class) {
            parseId = Long.valueOf(id.toString());
        }
        Optional<DeliverableDTO> deliverable = service.findById(parseId);
        if (deliverable.isPresent()) {
            return ResponseEntity.ok(deliverable);
        } else {
            return ResponseEntity.badRequest().body("Bad request.");
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listAll() {
        Optional<List<DeliverableDTO>> deliverables = Optional.ofNullable(service.findAll());
        if (deliverables.isPresent()) {
            return ResponseEntity.ok(deliverables);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Object id) {
        var parseId = id;
        if (service.getClass() == JpaDeliverableService.class) {
            parseId = Long.valueOf(id.toString());
        }
        Optional<DeliverableDTO> deliverable = service.findById(parseId);
        if(deliverable.isPresent()) {
            service.deleteById(parseId);
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody(required = false) DeliverableDTO dto) {
        if(dto==null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        var parseId = dto.getId();
        if(service.getClass() == JpaDeliverableService.class) {
            parseId = Long.valueOf(dto.getId().toString());
        }
        Optional<DeliverableDTO> deliverable = service.findById(parseId);
        if(deliverable.isPresent()) {
            service.delete(deliverable.get());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> add(@RequestBody(required = false) DeliverableDTO dto) {
        if(dto==null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        
        Optional<DeliverableDTO> newDeliverable = Optional.ofNullable((DeliverableDTO) service.save(dto));
        if(newDeliverable.isPresent()) {
            return ResponseEntity.ok(newDeliverable);
        } else {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody(required = false) DeliverableDTO dto, @PathVariable Object id) {
        if(dto==null) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        
        var parseId = id;
        if(service.getClass() == JpaDeliverableService.class) {
            parseId = Long.valueOf(id.toString());
        }
        Optional<DeliverableDTO> deliverable = service.findById(parseId);
        if(deliverable.isPresent()) {
            if(dto.getCourseId() != null) {
                deliverable.get().setCourseId(dto.getCourseId());
            }
            if(dto.getStudentId() != null) {
                deliverable.get().setStudentId(dto.getStudentId());
            }
            if(dto.getGrade() != null){
                deliverable.get().setGrade(dto.getGrade());
            }
            if(dto.getGradeName() != null){
                deliverable.get().setGradeName(dto.getGradeName());
            }
            if(dto.getPonderation() != null) {
                deliverable.get().setPonderation(dto.getPonderation());
            }
            return ResponseEntity.ok(service.save(deliverable.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
