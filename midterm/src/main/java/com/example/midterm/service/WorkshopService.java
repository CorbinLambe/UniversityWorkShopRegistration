package com.example.midterm.service;
import java.time.LocalDateTime;
import java.util.List;
import com.example.midterm.model.Workshop;
import com.example.midterm.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    public List<Workshop> getAllWorkshops(){
        return workshopRepository.findAll();
    }

    public List<Workshop> getUpcomingWorkshops(){
        return workshopRepository.findByStart_datetimeAfter(LocalDateTime.now());
    }

    public Workshop getWorkshopById(Long id){
        return workshopRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop could not be found"));
    }

    public Workshop createWorkshop(Workshop workshop){
        workshop.setSeats_remaining(workshop.getTotal_seats());
        workshop.setStatus("Active");
        return workshopRepository.save(workshop);
    }

    public Workshop updateWorkshop(Long id, Workshop workshopDetails){
        Workshop workshop = getWorkshopById(id);
        workshop.setTitle(workshopDetails.getTitle());
        workshop.setDescription(workshopDetails.getDescription());
        workshop.setLocation(workshopDetails.getLocation());
        workshop.setStart_datetime(workshopDetails.getStart_datetime());
        workshop.setTotal_seats(workshopDetails.getTotal_seats());
        return workshopRepository.save(workshop);
    }

    public void cancelWorkshop(Long id){
        Workshop workshop = getWorkshopById(id);
        workshop.setStatus("Cancelled");
        workshopRepository.save(workshop);
    }

}
