package com.example.midterm.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;
    @Column(unique = true)
    @ManyToOne
    @JoinColumn(name = "workshop_id", nullable = false)
    private Workshop workshop_id;
    private String status;
    LocalDateTime created_at;
    LocalDateTime cancelled_at;

    public Registration(Long id, LocalDateTime cancelled_at, LocalDateTime created_at, String status, Workshop workshop_id, User user_id) {
        this.id = id;
        this.cancelled_at = cancelled_at;
        this.created_at = created_at;
        this.status = status;
        this.workshop_id = workshop_id;
        this.user_id = user_id;
    }

    public Registration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCancelled_at() {
        return cancelled_at;
    }

    public void setCancelled_at(LocalDateTime cancelled_at) {
        this.cancelled_at = cancelled_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Workshop getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(Workshop workshop_id) {
        this.workshop_id = workshop_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", workshop_id=" + workshop_id +
                ", status='" + status + '\'' +
                ", created_at=" + created_at +
                ", cancelled_at=" + cancelled_at +
                '}';
    }
}
