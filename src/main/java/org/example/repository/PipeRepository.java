package org.example.repository;

import org.example.model.Pipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PipeRepository extends JpaRepository<Pipe, Long> {
}