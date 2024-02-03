package dev.fernando.proyecto.persistence.mongodb.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Table(name = "deliverble")
public class DeliverableMongoDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private Long courseId;
    private Long studentId;
    private Double grade;
    private String gradeName;
    private Double ponderation;
    
    public DeliverableMongoDB() {
    }
    
    public DeliverableMongoDB(Long courseId, Long studentId, Double grade, String gradeName, Double ponderation) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
        this.gradeName = gradeName;
        this.ponderation = ponderation;
    }
    
    public DeliverableMongoDB(String id, Long courseId, Long studentId, Double grade, String gradeName, Double ponderation) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
        this.gradeName = gradeName;
        this.ponderation = ponderation;
    }
    
    public String getId() {
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
