package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pipes")
public class Pipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromNodeId; // Where the pipe starts
    private Long toNodeId;   // Where the pipe ends
    private double length;
    private double capacity;

    @Enumerated(EnumType.STRING)
    private PipeStatus status; // Can be OK, LOW_PRESSURE, or BURST

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFromNodeId() { return fromNodeId; }
    public void setFromNodeId(Long fromNodeId) { this.fromNodeId = fromNodeId; }

    public Long getToNodeId() { return toNodeId; }
    public void setToNodeId(Long toNodeId) { this.toNodeId = toNodeId; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public PipeStatus getStatus() { return status; }
    public void setStatus(PipeStatus status) { this.status = status; }
}

enum PipeStatus {
    OK,
    LOW_PRESSURE,
    BURST
}