package dev.fernando.proyecto.persistence.mongodb.document;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Table(name = "enrol")
public class EnrolMongoDB {
    
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
    
    public EnrolMongoDB(Boolean active, Boolean successfulCourse) {
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public EnrolMongoDB(String courseId, String studentId, Boolean active, Boolean successfulCourse) {
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
}
