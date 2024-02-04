package dev.fernando.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class ProyectoConfiguration {
    
    @Autowired
    private org.springframework.core.env.Environment env;
    
    @Bean
    public MongoTemplate mongoTemplate() {
        String localHostMongo = env.getProperty("MONGODB_LOCALHOST");
        
        MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoClientDatabaseFactory(localHostMongo));
        
        mongoTemplate.getDb().drop();
        
        return mongoTemplate;
    }
}
