package dev.fernando.proyecto.persistence.postgresql.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deliverables")
public class DeliverablesPostgreSQL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long courseId;
    private Long studentId;
    private Double grade;
    private String gradeName;
    private Double ponderation;
    
    public DeliverablesPostgreSQL() {
    }
    
    public DeliverablesPostgreSQL(Long courseId, Long studentId, Double grade, String gradeName, Double ponderation) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
        this.gradeName = gradeName;
        this.ponderation = ponderation;
    }
    
    public DeliverablesPostgreSQL(Long id, Long courseId, Long studentId, Double grade, String gradeName, Double ponderation) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
        this.gradeName = gradeName;
        this.ponderation = ponderation;
    }
    
    public Long getId() {
        return id;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public Double getGrade() {
        return grade;
    }
    
    public void setGrade(Double grade) {
        this.grade = grade;
    }
    
    public String getGradeName() {
        return gradeName;
    }
    
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    
    public Double getPonderation() {
        return ponderation;
    }
    
    public void setPonderation(Double ponderation) {
        this.ponderation = ponderation;
    }
}
