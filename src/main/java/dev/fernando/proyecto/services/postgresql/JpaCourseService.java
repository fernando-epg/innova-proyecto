package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.persistence.postgresql.entity.CoursePostgreSQL;
import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLCourseRepository;
import dev.fernando.proyecto.services.ICourseGenericService;
import dev.fernando.proyecto.services.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

// @Service
public class JpaCourseService implements ICourseGenericService<CourseDTO,Long> {
    final private IPostgreSQLCourseRepository userRepository;
    
    @Autowired
    public JpaCourseService(IPostgreSQLCourseRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public CourseDTO save(CourseDTO entity) {
        CoursePostgreSQL newCourse = dtoConverter(entity);
        CoursePostgreSQL result = userRepository.save(newCourse);
        return entityConverter(result);
    }
    
    @Override
    public Optional<CourseDTO> findById(Long id) {
        return Optional.empty();
    }
    
    @Override
    public List<CourseDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(Long id) {
    
    }
    
    @Override
    public void delete(CourseDTO entity) {
    
    }
    
    private CoursePostgreSQL dtoConverter(CourseDTO dto) {
        return new CoursePostgreSQL(
                dto.getCode(),
                dto.getName(),
                dto.getCredits()
        );
    }
    
    private CourseDTO entityConverter(CoursePostgreSQL entity) {
        return new CourseDTO(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getCredits()
        );
    }
    
}
