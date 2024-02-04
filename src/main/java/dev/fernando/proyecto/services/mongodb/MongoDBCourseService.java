package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.persistence.mongodb.document.CourseMongoDB;
import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBCourseRepository;
import dev.fernando.proyecto.interfaces.ICourseGenericService;
import dev.fernando.proyecto.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MongoDBCourseService implements ICourseGenericService<CourseDTO, String> {
    final private IMongoDBCourseRepository repository;
    
    @Autowired
    public MongoDBCourseService(IMongoDBCourseRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public CourseDTO save(CourseDTO entity) {
        CourseMongoDB newCourse = dtoConverter(entity);
        CourseMongoDB result = repository.save(newCourse);
        return entityConverter(result);
    }
    
    @Override
    public Optional<CourseDTO> findById(String id) {
        Optional<CourseMongoDB> course = repository.findById(id);
        if (course.isPresent()) {
            return Optional.of(entityConverter(course.get()));
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    public List<CourseDTO> findAll() {
        List<CourseMongoDB> result = repository.findAll();
        List<CourseDTO> resultDto = new ArrayList<>();
        for (CourseMongoDB course : result) {
            CourseDTO dtoEntry = entityConverter(course);
            resultDto.add(dtoEntry);
        }
        return resultDto;
    }
    
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(CourseDTO dto) {
        repository.delete(dtoConverter(dto));
    }
    
    private CourseMongoDB dtoConverter(CourseDTO dto) {
        if (dto.getId() != null) {
            return new CourseMongoDB(
                    (String) dto.getId(),
                    dto.getCode(),
                    dto.getName(),
                    dto.getCredits()
            );
        }
        return new CourseMongoDB(
                dto.getCode(),
                dto.getName(),
                dto.getCredits()
        );
    }
    
    private CourseDTO entityConverter(CourseMongoDB entity) {
        return new CourseDTO(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getCredits()
        );
    }
}
