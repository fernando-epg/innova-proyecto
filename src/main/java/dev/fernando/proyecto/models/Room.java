package dev.fernando.proyecto.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static dev.fernando.proyecto.models.EStatus.RESERVED;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roomNumber;
    private String name;
    private String status;
    private LocalDate date;
    private Integer numAdults;
    private Integer numMinors;
    @Transient
    private int totalGuests;
    private boolean active;
    
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    
    public Room() {
    }
    
    public Room(Integer roomNumber, String name, Integer numAdults, Integer numMinors, String date) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.status = RESERVED.label;
        this.numAdults = numAdults;
        this.numMinors = numMinors;
        this.totalGuests = numAdults + numMinors;
        this.active = true;
        this.date = LocalDate.parse(date,dateTimeFormatter);
    }
    
    public Long getId() {
        return id;
    }
    
    public Integer getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(EStatus eStatus) {
        this.status = eStatus.label;
    }
    
    public Integer getNumAdults() {
        return numAdults;
    }
    
    public void setNumAdults(Integer numAdults) {
        this.numAdults = numAdults;
    }
    
    public Integer getNumMinors() {
        return numMinors;
    }
    
    public void setNumMinors(Integer numMinors) {
        this.numMinors = numMinors;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public Integer getTotalGuests() {
        return this.numAdults + this.numMinors;
    }
    
    public String getDate() {
        return dateTimeFormatter.format(this.date);
    }
    
    public void setDate(String date) {
        this.date = LocalDate.parse(date,dateTimeFormatter);
    }
    
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", numAdults=" + numAdults +
                ", numMinors=" + numMinors +
                ", date=" + date.format(dateTimeFormatter) +
                ", totalGuests=" + totalGuests +
                '}';
    }
}
