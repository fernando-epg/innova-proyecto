package dev.fernando.proyecto.dto;

import java.io.Serializable;

public class EnrolDTO implements Serializable {
    private Object id;
    private Object courseId;
    private Object studentId;
    private Boolean active;
    private Boolean successfulCourse;
    
    public EnrolDTO() {
    }
    
    public EnrolDTO(Object courseId, Object studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = true;
        this.successfulCourse = false;
    }
    
    public EnrolDTO(Object id, Object courseId, Object studentId, Boolean active, Boolean successfulCourse) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public EnrolDTO(Object courseId, Object studentId, Boolean active, Boolean successfulCourse) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public Boolean getSuccessfulCourse() {
        return successfulCourse;
    }
    
    public Object getCourseId() {
        return courseId;
    }
    
    public Object getStudentId() {
        return studentId;
    }
    
    public Object getId() {
        return id;
    }
    
    public void setCourseId(Object courseId) {
        this.courseId = courseId;
    }
    
    public void setStudentId(Object studentId) {
        this.studentId = studentId;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public void setSuccessfulCourse(Boolean successfulCourse) {
        this.successfulCourse = successfulCourse;
    }
}
