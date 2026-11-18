package org.example.repository;

import org.example.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;

// By extending JpaRepository, Spring automatically gives you built-in
// methods like save(), findAll(), findById(), and delete() for your Node table!
public interface NodeRepository extends JpaRepository<Node, Long> {
}