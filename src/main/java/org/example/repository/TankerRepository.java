package org.example.repository;

import org.example.model.Tanker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TankerRepository extends JpaRepository<Tanker, Long> {
}