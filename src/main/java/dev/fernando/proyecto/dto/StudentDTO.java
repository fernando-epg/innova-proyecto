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
    
    public void setId(Object id) {
        this.id = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public void setGender(EGender gender) {
        this.gender = gender;
    }
    
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
