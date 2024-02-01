/*
package dev.fernando.proyecto.persistence.postgresql;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class GradePostgreSQL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CoursePostgreSQL coursePostgreSQL;
    private StudentPostgreSQL studentPostgreSQL;
    private Double grade;
    private String gradeName;
    private Double ponderation;
    
    public GradePostgreSQL() {
    }
    
    public GradePostgreSQL(CoursePostgreSQL coursePostgreSQL, StudentPostgreSQL studentPostgreSQL, Double grade, String gradeName, Double ponderation) {
        this.coursePostgreSQL = coursePostgreSQL;
        this.studentPostgreSQL = studentPostgreSQL;
        this.grade = grade;
        this.gradeName = gradeName;
        this.ponderation = ponderation;
    }
    
    public Long getId() {
        return id;
    }
    
    public CoursePostgreSQL getCourse() {
        return coursePostgreSQL;
    }
    
    public void setCourse(CoursePostgreSQL coursePostgreSQL) {
        this.coursePostgreSQL = coursePostgreSQL;
    }
    
    public StudentPostgreSQL getStudent() {
        return studentPostgreSQL;
    }
    
    public void setStudent(StudentPostgreSQL studentPostgreSQL) {
        this.studentPostgreSQL = studentPostgreSQL;
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
*/
