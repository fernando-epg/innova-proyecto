package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.dto.DeliverableDTO;
import dev.fernando.proyecto.interfaces.IDeliverableGenericService;
import dev.fernando.proyecto.persistence.mongodb.document.DeliverableMongoDB;
import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBDeliverableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoDBDeliverableService implements IDeliverableGenericService<DeliverableDTO,String> {
    
    private final IMongoDBDeliverableRepository deliverableRepository;
    
    @Autowired
    public MongoDBDeliverableService(IMongoDBDeliverableRepository deliverableRepository) {
        this.deliverableRepository = deliverableRepository;
    }
    
    @Override
    public DeliverableDTO save(DeliverableDTO dto) {
        DeliverableMongoDB newDeliverable = dtoConverter(dto);
        DeliverableMongoDB result = deliverableRepository.save(newDeliverable);
        return entityConverter(result);
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
    
    private DeliverableMongoDB dtoConverter(DeliverableDTO dto) {
        return new DeliverableMongoDB(
                (Long) dto.getCourseId(),
                (Long) dto.getStudentId(),
                dto.getGrade(),
                dto.getGradeName(),
                dto.getPonderation()
        );
    }
    
    @Override
    public Optional<DeliverableDTO> findById(String id) {
        return Optional.empty();
    }
    
    @Override
    public List<DeliverableDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(String id) {
    
    }
    
    @Override
    public void delete(DeliverableDTO dto) {
    
    }
}
