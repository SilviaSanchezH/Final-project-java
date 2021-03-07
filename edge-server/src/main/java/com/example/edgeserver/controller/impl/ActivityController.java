package com.example.edgeserver.controller.impl;

import com.example.edgeserver.controller.dto.ActivityDTO;
import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import com.example.edgeserver.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/activities")
    @ResponseStatus(HttpStatus.OK)
    public List<ActivityDTO> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/activity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActivityDTO getActivity(@PathVariable Long id) {
        return activityService.getActivity(id);
    }

    @GetMapping("/activities/center/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ActivityDTO> getActivitiesByCenter(@PathVariable Long id) {
        return activityService.getActivitiesByCenter(id);
    }

    @GetMapping("/activity/{id}/workers")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkerDTO> getWorkersByActivity(@PathVariable Long id) {
        return activityService.getWorkersByActivity(id);
    }

    @GetMapping("/activity/{id}/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getClientsByActivity(@PathVariable Long id) {
        return activityService.getClientsByActivity(id);
    }

    @PostMapping("/activity")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityDTO addActivity(@RequestBody @Valid ActivityDTO activityDTO) {
        return activityService.addActivity(activityDTO);
    }

    @PutMapping("/activity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActivityDTO updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityDTO activityDTO) {
        activityDTO.setId(id);
        return activityService.updateActivity(id, activityDTO);
    }

    @DeleteMapping("/activity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}
