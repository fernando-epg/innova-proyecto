package dev.fernando.proyecto.persistence.mongodb.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Table(name = "course")
public class CourseMongoDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String code;
    private String name;
    private Double credits;
    
    public CourseMongoDB() {
    }
    
    public CourseMongoDB(String code, String name, Double credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }
    
    public CourseMongoDB(String id, String code, String name, Double credits) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
    }
    
    public String getId() {
        return id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getCredits() {
        return credits;
    }
    
    public void setCredits(Double credits) {
        this.credits = credits;
    }
}
