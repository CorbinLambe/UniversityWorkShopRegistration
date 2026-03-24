package com.example.midterm.controller;

import com.example.midterm.model.Registration;
import com.example.midterm.model.User;
import com.example.midterm.repository.UserRepository;
import com.example.midterm.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/workshop/{id}/registartion")
    public ResponseEntity<Registration> registerForWorkshop(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId){
        return new ResponseEntity<>(registrationService.registerForWorkshop(userId,id), HttpStatus.CREATED);
    }

    @DeleteMapping("/registration/{registrationId}")
    public ResponseEntity<Void> cancelRegistration(
            @PathVariable Long registrationId,
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole){
        boolean isAdmin = "Admin".equals(userRole);
        registrationService.cancelRegistration(registrationId,userId,isAdmin);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/registrations")
    public ResponseEntity<List<Registration>> getMyRegistrations(
            @RequestHeader("X-User-Id") Long userId) {
        List<Registration> registrations = registrationService.getUserRegistrations(userId);
        return ResponseEntity.ok(registrations);
    }


}
