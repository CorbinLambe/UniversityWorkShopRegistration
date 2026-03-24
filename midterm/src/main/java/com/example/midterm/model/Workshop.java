package com.example.midterm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String location;
    LocalDateTime start_datetime;
    private Integer total_seats;
    private Integer seats_remaining;
    private String status;

    public Workshop(Long id, String status, Integer seats_remaining, Integer total_seats, LocalDateTime start_datetime, String location, String description, String title) {
        this.id = id;
        this.status = status;
        this.seats_remaining = seats_remaining;
        this.total_seats = total_seats;
        this.start_datetime = start_datetime;
        this.location = location;
        this.description = description;
        this.title = title;
    }

    public Workshop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSeats_remaining() {
        return seats_remaining;
    }

    public void setSeats_remaining(Integer seats_remaining) {
        this.seats_remaining = seats_remaining;
    }

    public Integer getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(Integer total_seats) {
        this.total_seats = total_seats;
    }

    public LocalDateTime getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(LocalDateTime start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", start_datetime=" + start_datetime +
                ", total_seats=" + total_seats +
                ", seats_remaining=" + seats_remaining +
                ", status='" + status + '\'' +
                '}';
    }
}
