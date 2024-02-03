package dev.fernando.proyecto.dto;

public class DeliverableDTO {
    
    private Object id;
    private Object courseId;
    private Object studentId;
    private Double grade;
    private String gradeName;
    private Double ponderation;
    
    public DeliverableDTO() {
    }
    
    public DeliverableDTO(Object courseId, Object studentId, Double grade, String gradeName, Double ponderation) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
        this.gradeName = gradeName;
        this.ponderation = ponderation;
    }
    
    public DeliverableDTO(Object id, Object courseId, Object studentId, Double grade, String gradeName, Double ponderation) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
        this.gradeName = gradeName;
        this.ponderation = ponderation;
    }
    
    public Object getId() {
        return id;
    }
    
    public Object getCourseId() {
        return courseId;
    }
    
    public Object getStudentId() {
        return studentId;
    }
    
    public Double getGrade() {
        return grade;
    }
    
    public String getGradeName() {
        return gradeName;
    }
    
    public Double getPonderation() {
        return ponderation;
    }
}
