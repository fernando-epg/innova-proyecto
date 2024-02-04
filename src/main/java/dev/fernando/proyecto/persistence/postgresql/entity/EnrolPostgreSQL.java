package dev.fernando.proyecto.persistence.postgresql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "enroll")
public class EnrolPostgreSQL {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "student_id")
    private Long studentId;
    
    @Column(name = "course_id")
    private Long courseId;
    private Boolean active;
    private Boolean successfulCourse;
    
    public EnrolPostgreSQL() {
    }
    
    public EnrolPostgreSQL(Long courseId, Long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = true;
        this.successfulCourse = false;
    }
    
    public EnrolPostgreSQL(Long courseId, Long studentId, Boolean active, Boolean successfulCourse) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.active = active;
        this.successfulCourse = successfulCourse;
    }
    
    public EnrolPostgreSQL(Long id, Long courseId, Long studentId, Boolean active, Boolean successfulCourse) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
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
    
    public Long getStudentId() {
        return studentId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
