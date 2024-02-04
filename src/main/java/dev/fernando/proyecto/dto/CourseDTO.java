package dev.fernando.proyecto.dto;

import java.io.Serializable;

public class CourseDTO implements Serializable {
    private Object id;
    private String code;
    private String name;
    private Double credits;
    
    public CourseDTO() {
    }
    
    public CourseDTO(Object id, String code, String name, Double credits) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
    }
    
    public CourseDTO(String code, String name, Double credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }
    
    public Object getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Double getCredits() {
        return credits;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setId(Object id) {
        this.id = id;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCredits(Double credits) {
        this.credits = credits;
    }
}
