package dev.fernando.proyecto.persistence.postgresql.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class CoursePostgreSQL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double credits;
    
    public CoursePostgreSQL() {
    }
    
    public CoursePostgreSQL(String code, String name, Double credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
