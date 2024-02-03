package dev.fernando.proyecto.dto;

import dev.fernando.proyecto.persistence.EGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.time.LocalDate;

public class StudentDTO implements Serializable {
    private Object id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String personalEmail;
    private String contactPhone;
    
    
    public StudentDTO(Object id, String firstName, String lastName, LocalDate dob, EGender gender, String personalEmail, String contactPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.personalEmail = personalEmail;
        this.contactPhone = contactPhone;
    }
    
    public StudentDTO(String firstName, String lastName, LocalDate dob, EGender gender, String personalEmail, String contactPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.personalEmail = personalEmail;
        this.contactPhone = contactPhone;
    }
    
    public StudentDTO() {
    }
    
    public Object getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public LocalDate getDob() {
        return dob;
    }
    
    public EGender getGender() {
        return gender;
    }
    
    public String getPersonalEmail() {
        return personalEmail;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
}
