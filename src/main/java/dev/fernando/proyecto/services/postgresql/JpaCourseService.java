package dev.fernando.proyecto.services.postgresql;

import dev.fernando.proyecto.persistence.postgresql.entity.CoursePostgreSQL;
import dev.fernando.proyecto.persistence.postgresql.repository.IPostgreSQLCourseRepository;
import dev.fernando.proyecto.interfaces.ICourseGenericService;
import dev.fernando.proyecto.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class JpaCourseService implements ICourseGenericService<CourseDTO,Long> {
    final private IPostgreSQLCourseRepository repository;
    
    @Autowired
    public JpaCourseService(IPostgreSQLCourseRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public CourseDTO save(CourseDTO entity) {
        CoursePostgreSQL newCourse = dtoConverter(entity);
        CoursePostgreSQL result = repository.save(newCourse);
        return entityConverter(result);
    }
    
    @Override
    public Optional<CourseDTO> findById(Long id) {
        Optional<CoursePostgreSQL> course = repository.findById(id);
        if(course.isPresent()) {
            return Optional.of(entityConverter(course.get()));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public List<CourseDTO> findAll() {
        List<CoursePostgreSQL> result = repository.findAll();
        List<CourseDTO> resultDto = new ArrayList<>();
        for (CoursePostgreSQL course : result) {
            CourseDTO dtoEntry = entityConverter(course);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(CourseDTO dto) {
        repository.delete(dtoConverter(dto));
    }
    
    private CoursePostgreSQL dtoConverter(CourseDTO dto) {
        if(dto.getId() != null) {
            return new CoursePostgreSQL(
                    Long.valueOf(dto.getId().toString()),
                    dto.getCode(),
                    dto.getName(),
                    dto.getCredits()
            );
        }
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
