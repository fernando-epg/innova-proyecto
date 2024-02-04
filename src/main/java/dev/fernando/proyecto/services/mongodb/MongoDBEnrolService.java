package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.dto.EnrolDTO;
import dev.fernando.proyecto.interfaces.IEnrolGenericService;
import dev.fernando.proyecto.persistence.mongodb.document.EnrolMongoDB;
import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBEnrolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MongoDBEnrolService implements IEnrolGenericService<EnrolDTO,String> {
    private final IMongoDBEnrolRepository repository;
    
    @Autowired
    public MongoDBEnrolService(IMongoDBEnrolRepository repository) {
        this.repository = repository;
    }
    
    
    @Override
    public EnrolDTO save(EnrolDTO dto) {
        EnrolMongoDB newEnrol = dtoConverter(dto);
        EnrolMongoDB result = repository.save(newEnrol);
        return entityConverter(result);
    }
    
    @Override
    public Optional<EnrolDTO> findById(String id) {
        Optional<EnrolMongoDB> enrol = repository.findById(id);
        if(enrol.isPresent()) {
            return Optional.of(entityConverter(enrol.get()));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public List<EnrolDTO> findAll() {
        List<EnrolMongoDB> result = repository.findAll();
        List<EnrolDTO> resultDto = new ArrayList<>();
        for (EnrolMongoDB course : result) {
            EnrolDTO dtoEntry = entityConverter(course);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(EnrolDTO dto) {
        repository.delete(dtoConverter(dto));
    }
    
    private EnrolMongoDB dtoConverter(EnrolDTO dto) {
        if(dto.getId() != null) {
            return new EnrolMongoDB(
                    String.valueOf(dto.getId()),
                    String.valueOf(dto.getCourseId()),
                    String.valueOf(dto.getStudentId()),
                    dto.getActive(),
                    dto.getSuccessfulCourse()
            );
        }
        
        return new EnrolMongoDB(
                String.valueOf(dto.getCourseId()),
                String.valueOf(dto.getStudentId()),
                dto.getActive(),
                dto.getSuccessfulCourse()
        );
    }
    
    private EnrolDTO entityConverter(EnrolMongoDB entity) {
        return new EnrolDTO(
                entity.getId(),
                entity.getCourseId(),
                entity.getStudentId(),
                entity.getActive(),
                entity.getSuccessfulCourse()
        );
    }
}
