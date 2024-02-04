package dev.fernando.proyecto.persistence.mongodb.document;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Table(name = "enrol")
public class EnrolMongoDB {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String courseId;
    private String studentId;
    private Boolean active = true;
    private Boolean successfulCourse = false;
    
    public EnrolMongoDB() {
    }
    
    public EnrolMongoDB(String courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
    
    public EnrolMongoDB(String courseId, String studentId, Boolean active, Boolean successfulCourse) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public EnrolMongoDB(String id, String courseId, String studentId, Boolean active, Boolean successfulCourse) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
    
    public String getId() {
        return id;
    }
}
