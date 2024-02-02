package dev.fernando.proyecto.persistence.mongodb.repository;

import dev.fernando.proyecto.persistence.mongodb.document.EnrolMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoDBEnrolRepository extends MongoRepository<EnrolMongoDB,String> {
}
