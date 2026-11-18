package org.example.model;

import jakarta.persistence.*;

@Entity // Tells Java this class represents a database table
@Table(name = "nodes")
public class Node {

    @Id // This is the unique ID (Primary Key) for each row
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private NodeType type; // Can be SOURCE, JUNCTION, or COMMUNITY

    private int population;
    private double demand; // How much water they need

    // Getters and Setters (standard Java methods to read/write data)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public NodeType getType() { return type; }
    public void setType(NodeType type) { this.type = type; }
}

enum NodeType { SOURCE, JUNCTION, COMMUNITY }