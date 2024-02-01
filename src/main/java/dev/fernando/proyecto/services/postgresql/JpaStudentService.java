package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLStudentRepository;
import dev.fernando.proyecto.persistence.postgresql.entity.StudentPostgreSQL;
import dev.fernando.proyecto.services.IStudentGenericService;
import dev.fernando.proyecto.services.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

//@Service
public class JpaStudentService implements IStudentGenericService<StudentDTO,Long> {
    final private IPostgreSQLStudentRepository userRepository;
    
    @Autowired
    public JpaStudentService(IPostgreSQLStudentRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public StudentDTO save(StudentDTO entity) {
        StudentPostgreSQL newStudent = dtoConverter(entity);
        StudentPostgreSQL result = userRepository.save(newStudent);
        return entityConverter(result);
    }
    
    @Override
    public Optional<StudentDTO> findById(Long id) {
        Optional<StudentPostgreSQL> student = userRepository.findById(id);
        if(student.isPresent()) {
            return Optional.of(entityConverter(student.get()));
        } else {
            return Optional.of(new StudentDTO());
        }
    }
    
    @Override
    public List<StudentDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(Long id) {
    
    }
    
    @Override
    public void delete(StudentDTO entity) {
    
    }
    
    private StudentPostgreSQL dtoConverter(StudentDTO dto) {
        return new StudentPostgreSQL(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDob(),
                dto.getGender(),
                dto.getPersonalEmail(),
                dto.getContactPhone()
        );
    }
    
    private StudentDTO entityConverter(StudentPostgreSQL entity) {
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