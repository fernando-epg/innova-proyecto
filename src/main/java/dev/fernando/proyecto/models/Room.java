package dev.fernando.proyecto.models;

import jakarta.persistence.*;

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
    @Transient
    private int totalGuests;
    private boolean active;
    
    public Room() {
    }
    
    public Room(int roomNumber, String name, int numAdults, int numMinors) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.isBooked = true;
        this.numAdults = numAdults;
        this.numMinors = numMinors;
        this.totalGuests = numAdults + numMinors;
        this.active = true;
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
//        calculateTotalGuests();
    }
    
    public int getNumMinors() {
        return numMinors;
    }
    
    public void setNumMinors(int numMinors) {
        this.numMinors = numMinors;
//        calculateTotalGuests();
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    /*private void calculateTotalGuests() {
        this.totalGuests = this.numAdults + this.numMinors;
    }*/
    
    public int getTotalGuests() {
        return this.numAdults + this.numMinors;
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
                ", totalGuests=" + this.getTotalGuests() +
                '}';
    }
}
