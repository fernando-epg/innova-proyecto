package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.dto.EnrolDTO;
import dev.fernando.proyecto.interfaces.IEnrolGenericService;
import dev.fernando.proyecto.persistence.postgresql.entity.EnrolId;
import dev.fernando.proyecto.persistence.postgresql.entity.EnrolPostgreSQL;
import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLEnrolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class JpaEnrolService implements IEnrolGenericService<EnrolDTO,Long> {
    
    private final IPostgreSQLEnrolRepository enrolRepository;
    
    @Autowired
    public JpaEnrolService(IPostgreSQLEnrolRepository enrolRepository) {
        this.enrolRepository = enrolRepository;
    }
    
    @Override
    public EnrolDTO save(EnrolDTO entity) {
        EnrolPostgreSQL newEnrol = dtoConverter(entity);
        EnrolPostgreSQL result = enrolRepository.save(newEnrol);
        return entityConverter(result);
    }
    
    @Override
    public Optional<EnrolDTO> findById(Long id) {
        return Optional.empty();
    }
    
    @Override
    public List<EnrolDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(Long id) {
    
    }
    
    @Override
    public void delete(EnrolDTO entity) {
    
    }
    
    private EnrolPostgreSQL dtoConverter(EnrolDTO dto) {
        return new EnrolPostgreSQL(
                new EnrolId(Long.valueOf(dto.getStudentId()), Long.valueOf(dto.getCourseId()))
        );
    }
    
    private EnrolDTO entityConverter(EnrolPostgreSQL entity) {
        return new EnrolDTO(
                String.valueOf(entity.getId().getCourseId()),
                String.valueOf(entity.getId().getStudentId()),
                entity.getActive(),
                entity.getSuccessfulCourse()
        );
    }
}
