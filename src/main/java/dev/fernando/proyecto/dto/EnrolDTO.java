package dev.fernando.proyecto.dto;

import java.io.Serializable;

public class EnrolDTO implements Serializable {
    private Object id;
    private String courseId;
    private String studentId;
    private Boolean active;
    private Boolean successfulCourse;
    
    public EnrolDTO() {
    }
    
    public EnrolDTO(String courseId, String studentId, Boolean active, Boolean successfulCourse) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public EnrolDTO(Object id, String courseId, String studentId, Boolean active, Boolean successfulCourse) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public EnrolDTO(String courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public Boolean getSuccessfulCourse() {
        return successfulCourse;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public String getStudentId() {
        return studentId;
    }
}
