package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tankers")
public class Tanker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long locationNodeId; // Maps to location_node
    private double capacity;
    private boolean available;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLocationNodeId() { return locationNodeId; }
    public void setLocationNodeId(Long locationNodeId) { this.locationNodeId = locationNodeId; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}