package dev.fernando.proyecto.persistence.postgresql.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "enroll")
public class EnrolPostgreSQL {
    
    @EmbeddedId
    private EnrolId id;

    private Boolean active = true;
    private Boolean successfulCourse = false;
    
    public EnrolPostgreSQL() {
    }
    
    public EnrolPostgreSQL(EnrolId enrolId) {
        this.id = enrolId;
    }
    
    public EnrolPostgreSQL(EnrolId id, Boolean active, Boolean successfulCourse) {
        this.id = id;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public Boolean getSuccessfulCourse() {
        return successfulCourse;
    }
    
    public void setSuccessfulCourse(Boolean successfulCourse) {
        this.successfulCourse = successfulCourse;
    }
    
    public EnrolId getId() {
        return id;
    }
}
