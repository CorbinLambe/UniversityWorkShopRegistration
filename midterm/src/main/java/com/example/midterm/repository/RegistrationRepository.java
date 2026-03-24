package com.example.midterm.repository;
import java.util.List;
import java.util.Optional;

import com.example.midterm.model.Registration;
import com.example.midterm.model.User;
import com.example.midterm.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
    List<Registration> findByWorkShop(Workshop workshop);
    Boolean existsByUserAndWorkshop(User user, Workshop workshop);
    Optional<Registration> findByUserAndWorkshop(User user, Workshop workshop);

    @Query("SELECT COUNT(r) > 0 FROM Registration r WHERE r.user = :user AND r.workshop = :workshop AND r.status = 'ACTIVE'")
    boolean existsActiveRegistration(@Param("user") User user, @Param("workshop") Workshop workshop);
}
