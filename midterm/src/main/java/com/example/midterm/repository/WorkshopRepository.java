package com.example.midterm.repository;

import com.example.midterm.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
List<Workshop> findByStart_datetimeAfter(LocalDateTime date);
List<Workshop> findByStatus(String status);
}
