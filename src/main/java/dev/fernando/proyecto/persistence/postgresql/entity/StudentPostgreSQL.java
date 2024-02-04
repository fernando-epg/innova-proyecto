package dev.fernando.proyecto.persistence.postgresql.entity;

import dev.fernando.proyecto.persistence.EGender;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student")
public class StudentPostgreSQL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String personalEmail;
    private String contactPhone;
    
    public StudentPostgreSQL() {
    }
    
    public StudentPostgreSQL(String firstName, String lastName, LocalDate dob, EGender gender, String personalEmail, String contactPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.personalEmail = personalEmail;
        this.contactPhone = contactPhone;
    }
    
    public StudentPostgreSQL(Long id, String firstName, String lastName, LocalDate dob, EGender gender, String personalEmail, String contactPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.personalEmail = personalEmail;
        this.contactPhone = contactPhone;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
