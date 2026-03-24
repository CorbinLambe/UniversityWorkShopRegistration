package com.example.midterm.controller;

import com.example.midterm.model.Registration;
import com.example.midterm.model.Workshop;
import com.example.midterm.repository.RegistrationRepository;
import com.example.midterm.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private WorkshopService workshopService;

    @Autowired
    private RegistrationRepository registrationRepository;

    @PostMapping("/workshops")
    public ResponseEntity<Workshop> createWorkshop(@RequestBody Workshop workshop){
        return new ResponseEntity<>(workshopService.createWorkshop(workshop), HttpStatus.CREATED);
    }

    @PutMapping("/workshop/{id}")
    public ResponseEntity<Workshop> updateWorkshop(@PathVariable Long id, @RequestBody Workshop workshop){
        return ResponseEntity.ok(workshopService.updateWorkshop(id, workshop));
    }

    @PatchMapping("/workshop/{id}/cancel")
    public ResponseEntity<Void> cancelWorkshop(@PathVariable Long id){
        workshopService.cancelWorkshop(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/workshop/{id}/registration")
    public ResponseEntity<List<Registration>> getWorkshopRegistration(@PathVariable Long id){
        Workshop workshop = workshopService.getWorkshopById(id);
        return ResponseEntity.ok(registrationRepository.findByWorkShop(workshop));
    }
}
