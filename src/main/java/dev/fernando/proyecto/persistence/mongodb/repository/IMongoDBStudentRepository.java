package dev.fernando.proyecto.persistence.mongodb.repository;

import dev.fernando.proyecto.persistence.mongodb.document.StudentMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoDBStudentRepository extends MongoRepository<StudentMongoDB,String> {
}
