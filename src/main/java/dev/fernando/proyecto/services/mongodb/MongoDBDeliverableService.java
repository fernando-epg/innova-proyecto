package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.dto.DeliverableDTO;
import dev.fernando.proyecto.interfaces.IDeliverableGenericService;
import dev.fernando.proyecto.persistence.mongodb.document.DeliverableMongoDB;
import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBDeliverableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class MongoDBDeliverableService implements IDeliverableGenericService<DeliverableDTO,String> {
    
    private final IMongoDBDeliverableRepository repository;
    
    @Autowired
    public MongoDBDeliverableService(IMongoDBDeliverableRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public DeliverableDTO save(DeliverableDTO dto) {
        DeliverableMongoDB newDeliverable = dtoConverter(dto);
        DeliverableMongoDB result = repository.save(newDeliverable);
        return entityConverter(result);
    }
    
    @Override
    public Optional<DeliverableDTO> findById(String id) {
        Optional<DeliverableMongoDB> deliverable = repository.findById(id);
        if(deliverable.isPresent()) {
            return Optional.of(entityConverter(deliverable.get()));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public List<DeliverableDTO> findAll() {
        List<DeliverableMongoDB> result = repository.findAll();
        List<DeliverableDTO> resultDto = new ArrayList<>();
        for (DeliverableMongoDB course : result) {
            DeliverableDTO dtoEntry = entityConverter(course);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(DeliverableDTO dto) {
        repository.delete(dtoConverter(dto));
    }
    
    private DeliverableMongoDB dtoConverter(DeliverableDTO dto) {
        if(dto.getId() != null) {
            return new DeliverableMongoDB(
                    String.valueOf(dto.getId()),
                    String.valueOf(dto.getCourseId()),
                    String.valueOf(dto.getStudentId()),
                    dto.getGrade(),
                    dto.getGradeName(),
                    dto.getPonderation()
            );
        }
        
        return new DeliverableMongoDB(
                String.valueOf(dto.getCourseId()),
                String.valueOf(dto.getStudentId()),
                dto.getGrade(),
                dto.getGradeName(),
                dto.getPonderation()
        );
    }
    
    private DeliverableDTO entityConverter(DeliverableMongoDB result) {
        return new DeliverableDTO(
                result.getId(),
                result.getCourseId(),
                result.getStudentId(),
                result.getGrade(),
                result.getGradeName(),
                result.getPonderation()
        );
    }
}
