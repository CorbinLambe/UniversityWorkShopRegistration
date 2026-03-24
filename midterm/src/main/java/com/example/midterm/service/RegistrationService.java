package com.example.midterm.service;


import com.example.midterm.model.Registration;
import com.example.midterm.model.User;
import com.example.midterm.model.Workshop;
import com.example.midterm.repository.RegistrationRepository;
import com.example.midterm.repository.UserRepository;
import com.example.midterm.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Transactional
    public Registration registerForWorkshop(Long userId, Long workshopId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        Workshop workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found"));

        if (workshop.getStart_datetime().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot register Past workshops");
        }

        if (workshop.getSeats_remaining() <= 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Workshop is full");
        }

        Optional<Registration> existingRegistration = registrationRepository.findByUserAndWorkshop(user, workshop);
        if (existingRegistration.isPresent() && "ACTIVE".equals(existingRegistration.get().getStatus())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You already registered for this workshop");
        }

        //Create user
        Registration registration = new Registration();
        registration.setUser_id(user);
        registration.setWorkshop_id(workshop);
        registration.setStatus("Active");
        registration.setCreated_at(LocalDateTime.now());

        //Decrease seats
        workshop.setSeats_remaining(workshop.getSeats_remaining() -1);
        workshopRepository.save(workshop);

        return registrationRepository.save(registration);

    }

    @Transactional
    public void cancelRegistration(Long registrationId, Long userId, boolean isAdmin){
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registration not found"));
        if(!isAdmin && !registration.getUser_id().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot cancel user registration");
        }
        Workshop workshop = registration.getWorkshop_id();
        if (workshop.getStart_datetime().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot cancel after workshop has started");
        }

        if("CANCELLED".equals(registration.getStatus())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Registration has already been canceled");
        }

        //Cancel Registration
        registration.setStatus("CANCELLED");
        registration.setCancelled_at(LocalDateTime.now());
        registrationRepository.save(registration);

        //Increase seats remaining
        workshop.setSeats_remaining(workshop.getSeats_remaining() +1);
        workshopRepository.save(workshop);
    }

    public List<Registration> getUserRegistrations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return registrationRepository.findByUser(user);
    }
}
