package dev.fernando.proyecto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private String name;
    private boolean isBooked = false;
    private int numAdults;
    private int numMinors;
    private int totalGuests;
    
    public Room() {
    }
    
    public Room(int roomNumber, String name, int numAdults, int numMinors) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.isBooked = true;
        this.numAdults = numAdults;
        this.numMinors = numMinors;
        this.totalGuests = numAdults + numMinors;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public int getRoomNumber() {
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
    
    public boolean isBooked() {
        return isBooked;
    }
    
    public void setBooked(boolean booked) {
        isBooked = booked;
    }
    
    public int getNumAdults() {
        return numAdults;
    }
    
    public void setNumAdults(int numAdults) {
        this.numAdults = numAdults;
        calculateTotalGuests();
    }
    
    public int getNumMinors() {
        return numMinors;
    }
    
    public void setNumMinors(int numMinors) {
        this.numMinors = numMinors;
        calculateTotalGuests();
    }
    
    private void calculateTotalGuests() {
        this.totalGuests = this.numAdults + this.numMinors;
    }
    
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", name='" + name + '\'' +
                ", isBooked=" + isBooked +
                ", numAdults=" + numAdults +
                ", numMinors=" + numMinors +
                ", totalGuests=" + totalGuests +
                '}';
    }
}
