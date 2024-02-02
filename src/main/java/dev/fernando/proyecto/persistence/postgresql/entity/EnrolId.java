package dev.fernando.proyecto.persistence.postgresql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnrolId {
    @Column(name = "student_id")
    private Long studentId;
    
    @Column(name = "course_id")
    private Long courseId;
    
    public EnrolId() {
    }
    
    public EnrolId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
}
