package dev.fernando.proyecto.services.mongodb;

import dev.fernando.proyecto.persistence.mongodb.document.CourseMongoDB;
import dev.fernando.proyecto.persistence.mongodb.repository.IMongoDBCourseRepository;
import dev.fernando.proyecto.services.ICourseGenericService;
import dev.fernando.proyecto.services.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoDBCourseService implements ICourseGenericService<CourseDTO, String> {
    final private IMongoDBCourseRepository courseRepository;
    
    @Autowired
    public MongoDBCourseService(IMongoDBCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    @Override
    public CourseDTO save(CourseDTO entity) {
        CourseMongoDB newCourse = dtoConverter(entity);
        CourseMongoDB result = courseRepository.save(newCourse);
        return entityConverter(result);
    }
    
    @Override
    public Optional<CourseDTO> findById(String id) {
        return Optional.empty();
    }
    
    @Override
    public List<CourseDTO> findAll() {
        return null;
    }
    
    @Override
    public void deleteById(String id) {
    
    }
    
    @Override
    public void delete(CourseDTO entity) {
    
    }
    
    private CourseMongoDB dtoConverter(CourseDTO dto) {
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
