package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBStudentRepository;
import dev.fernando.proyecto.persistence.mongodb.document.StudentMongoDB;
import dev.fernando.proyecto.interfaces.IStudentGenericService;
import dev.fernando.proyecto.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MongoDBStudentService implements IStudentGenericService<StudentDTO,String> {
    
    final private IMongoDBStudentRepository userRepository;
    
    @Autowired
    public MongoDBStudentService(IMongoDBStudentRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public StudentDTO save(StudentDTO entity) {
        StudentMongoDB newStudent = dtoConverter(entity);
        StudentMongoDB result = userRepository.save(newStudent);
        return entityConverter(result);
    }
    
    @Override
    public Optional<StudentDTO> findById(String id) {
        return Optional.empty();
    }
    
    @Override
    public List<StudentDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(String id) {
    
    }
    
    @Override
    public void delete(StudentDTO entity) {
    
    }
    
    private StudentMongoDB dtoConverter(StudentDTO dto) {
        return new StudentMongoDB(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDob(),
                dto.getGender(),
                dto.getPersonalEmail(),
                dto.getContactPhone()
        );
    }
    
    private StudentDTO entityConverter(StudentMongoDB entity) {
        return new StudentDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDob(),
                entity.getGender(),
                entity.getPersonalEmail(),
                entity.getContactPhone()
        );
    }
}
