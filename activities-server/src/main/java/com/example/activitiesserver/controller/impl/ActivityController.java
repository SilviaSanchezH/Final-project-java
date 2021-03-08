package com.example.activitiesserver.controller.impl;

import com.example.activitiesserver.controller.dto.ActivityDTO;
import com.example.activitiesserver.controller.dto.ClientDTO;
import com.example.activitiesserver.controller.dto.WorkerDTO;
import com.example.activitiesserver.model.Activity;
import com.example.activitiesserver.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/activities")
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/activity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Activity getActivity(@PathVariable Long id) {
        return activityService.getActivity(id);
    }

    @GetMapping("/activities/center/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getActivitiesByCenter(@PathVariable Long id) {
        return activityService.getActivitiesByCenter(id);
    }

    @PostMapping("/activity/{activityId}/worker/{workerId}")
    @ResponseStatus(HttpStatus.OK)
    public void addWorkerToActivity(@PathVariable Long activityId, @PathVariable Long workerId) {
        activityService.addWorkerToActivity(activityId, workerId);
    }

    @PostMapping("/activity/{activityId}/client/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public void addClientToActivity(@PathVariable Long activityId, @PathVariable Long clientId) {
        activityService.addClientToActivity(activityId, clientId);
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
    public Activity addActivity(@RequestBody @Valid ActivityDTO activityDTO) {
        return activityService.addActivity(activityDTO);
    }

    @PutMapping("/activity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Activity updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityDTO activityDTO) {
        activityDTO.setId(id);
        return activityService.updateActivity(activityDTO);
    }

    @DeleteMapping("/activity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}
