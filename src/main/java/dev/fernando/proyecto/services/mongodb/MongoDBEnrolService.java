package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.dto.EnrolDTO;
import dev.fernando.proyecto.interfaces.IEnrolGenericService;
import dev.fernando.proyecto.persistence.mongodb.document.EnrolMongoDB;
import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBEnrolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MongoDBEnrolService implements IEnrolGenericService<EnrolDTO,String> {
    private final IMongoDBEnrolRepository enrolRepository;
    
    @Autowired
    public MongoDBEnrolService(IMongoDBEnrolRepository enrolRepository) {
        this.enrolRepository = enrolRepository;
    }
    
    
    @Override
    public EnrolDTO save(EnrolDTO dto) {
        EnrolMongoDB newEnrol = dtoConverter(dto);
        EnrolMongoDB result = enrolRepository.save(newEnrol);
        return entityConverter(result);
    }
    
    private EnrolDTO entityConverter(EnrolMongoDB entity) {
        return new EnrolDTO(
                entity.getCourseId(),
                entity.getStudentId(),
                entity.getActive(),
                entity.getSuccessfulCourse()
        );
    }
    
    private EnrolMongoDB dtoConverter(EnrolDTO dto) {
        return new EnrolMongoDB(
                dto.getStudentId(),
                dto.getCourseId()
        );
    }
    
    @Override
    public Optional<EnrolDTO> findById(String id) {
        return Optional.empty();
    }
    
    @Override
    public List<EnrolDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(String id) {
    
    }
    
    @Override
    public void delete(EnrolDTO entity) {
    
    }
}
