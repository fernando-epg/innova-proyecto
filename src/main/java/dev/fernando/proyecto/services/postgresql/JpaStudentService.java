package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLStudentRepository;
import dev.fernando.proyecto.persistence.postgresql.entity.StudentPostgreSQL;
import dev.fernando.proyecto.interfaces.IStudentGenericService;
import dev.fernando.proyecto.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaStudentService implements IStudentGenericService<StudentDTO,Long> {
    final private IPostgreSQLStudentRepository repository;
    
    @Autowired
    public JpaStudentService(IPostgreSQLStudentRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public StudentDTO save(StudentDTO entity) {
        StudentPostgreSQL newStudent = dtoConverter(entity);
        StudentPostgreSQL result = repository.save(newStudent);
        return entityConverter(result);
    }
    
    @Override
    public Optional<StudentDTO> findById(Long id) {
        Optional<StudentPostgreSQL> student = repository.findById(id);
        if(student.isPresent()) {
            return Optional.of(entityConverter(student.get()));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public List<StudentDTO> findAll() {
        List<StudentPostgreSQL> result = repository.findAll();
        List<StudentDTO> resultDto = new ArrayList<>();
        for (StudentPostgreSQL student : result) {
            StudentDTO dtoEntry = entityConverter(student);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(StudentDTO dto) {
        repository.delete(dtoConverter(dto));
    }
    
    private StudentPostgreSQL dtoConverter(StudentDTO dto) {
        if(dto.getId() != null) {
            return new StudentPostgreSQL(
                    Long.valueOf(dto.getId().toString()),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getDob(),
                    dto.getGender(),
                    dto.getPersonalEmail(),
                    dto.getContactPhone()
            );
        }
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