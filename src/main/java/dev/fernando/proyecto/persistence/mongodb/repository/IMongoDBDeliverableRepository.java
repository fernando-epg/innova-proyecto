package dev.fernando.proyecto.persistence.mongodb.repository;

import dev.fernando.proyecto.persistence.mongodb.document.DeliverableMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoDBDeliverableRepository extends MongoRepository<DeliverableMongoDB, String> {
}
