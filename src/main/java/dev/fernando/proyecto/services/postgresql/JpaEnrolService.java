package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.dto.EnrolDTO;
import dev.fernando.proyecto.interfaces.IEnrolGenericService;
import dev.fernando.proyecto.persistence.postgresql.entity.EnrolPostgreSQL;
import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLEnrolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class JpaEnrolService implements IEnrolGenericService<EnrolDTO, Long> {
    
    private final IPostgreSQLEnrolRepository repository;
    
    @Autowired
    public JpaEnrolService(IPostgreSQLEnrolRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public EnrolDTO save(EnrolDTO entity) {
        EnrolPostgreSQL newEnrol = dtoConverter(entity);
        EnrolPostgreSQL result = repository.save(newEnrol);
        return entityConverter(result);
    }
    
    @Override
    public Optional<EnrolDTO> findById(Long id) {
        Optional<EnrolPostgreSQL> enrol = repository.findById(id);
        if (enrol.isPresent()) {
            return Optional.of(entityConverter(enrol.get()));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public List<EnrolDTO> findAll() {
        List<EnrolPostgreSQL> result = repository.findAll();
        List<EnrolDTO> resultDto = new ArrayList<>();
        for (EnrolPostgreSQL enrol : result) {
            EnrolDTO dtoEntry = entityConverter(enrol);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(EnrolDTO dto) {
        repository.delete(dtoConverter(dto));
    }
    
    private EnrolPostgreSQL dtoConverter(EnrolDTO dto) {
        if(dto.getId() != null) {
            return new EnrolPostgreSQL(
                    Long.valueOf(dto.getId().toString()),
                    Long.valueOf(dto.getCourseId().toString()),
                    Long.valueOf(dto.getStudentId().toString()),
                    dto.getActive(),
                    dto.getSuccessfulCourse()
            );
        }
        
        return new EnrolPostgreSQL(
                Long.valueOf(dto.getCourseId().toString()),
                Long.valueOf(dto.getStudentId().toString()),
                dto.getActive(),
                dto.getSuccessfulCourse()
        );
    }
    
    private EnrolDTO entityConverter(EnrolPostgreSQL entity) {
        return new EnrolDTO(
                entity.getId(),
                entity.getCourseId(),
                entity.getStudentId(),
                entity.getActive(),
                entity.getSuccessfulCourse()
        );
    }
}
