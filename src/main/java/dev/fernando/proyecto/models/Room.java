package dev.fernando.proyecto.models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import static dev.fernando.proyecto.models.EStatus.RESERVED;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roomNumber;
    private String name;
    private String status;
    private Integer numAdults;
    private Integer numMinors;
    @Transient
    private int totalGuests;
    private boolean active;
    
    public Room() {
    }
    
    public Room(Integer roomNumber, String name, Integer numAdults, Integer numMinors) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.status = RESERVED.label;
        this.numAdults = numAdults;
        this.numMinors = numMinors;
        this.totalGuests = numAdults + numMinors;
        this.active = true;
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
    
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", name='" + name + '\'' +
                ", isBooked=" + status +
                ", numAdults=" + numAdults +
                ", numMinors=" + numMinors +
                ", totalGuests=" + this.getTotalGuests() +
                '}';
    }
}
