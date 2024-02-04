package dev.fernando.proyecto.persistence.mongodb.document;

import dev.fernando.proyecto.persistence.EGender;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Document
@Table(name="student")
public class StudentMongoDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String personalEmail;
    private String contactPhone;
    
    public StudentMongoDB() {
    }
    
    public StudentMongoDB(String id, String firstName, String lastName, String dob, EGender gender, String personalEmail, String contactPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.gender = gender;
        this.personalEmail = personalEmail;
        this.contactPhone = contactPhone;
    }
    
    public StudentMongoDB(String firstName, String lastName, String dob, EGender gender, String personalEmail, String contactPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = LocalDate.parse(dob,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.gender = gender;
        this.personalEmail = personalEmail;
        this.contactPhone = contactPhone;
    }
    
    public String getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getDob() {
        return dob;
    }
    
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public EGender getGender() {
        return gender;
    }
    
    public void setGender(EGender gender) {
        this.gender = gender;
    }
    
    public String getPersonalEmail() {
        return personalEmail;
    }
    
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
