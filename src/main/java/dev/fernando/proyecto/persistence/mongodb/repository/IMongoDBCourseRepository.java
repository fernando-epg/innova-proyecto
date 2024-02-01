package dev.fernando.proyecto.persistence.mongodb.repository;

import dev.fernando.proyecto.persistence.mongodb.document.CourseMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoDBCourseRepository extends MongoRepository<CourseMongoDB,String> {
}
