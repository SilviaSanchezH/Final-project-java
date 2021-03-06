package com.example.edgeserver.service;

import com.example.edgeserver.client.ActivityClient;
import com.example.edgeserver.controller.dto.ActivityDTO;
import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityClient activityClient;

    @GetMapping("/activities")
    public List<ActivityDTO> getAllActivities(){
        return activityClient.getAllActivities();
    }

    @GetMapping("/activity/{id}")
    public ActivityDTO getActivity(@PathVariable Long id){
        return activityClient.getActivity(id);
    }

    @GetMapping("/activities/center/{id}")
    public List<ActivityDTO> getActivitiesByCenter(@PathVariable Long id){
        return activityClient.getActivitiesByCenter(id);
    }

    @GetMapping("/activity/{id}/workers")
    public List<WorkerDTO> getWorkersByActivity(@PathVariable Long id){
        return activityClient.getWorkersByActivity(id);
    }

    @GetMapping("/activity/{id}/clients")
    public List<ClientDTO> getClientsByActivity(@PathVariable Long id){
        return activityClient.getClientsByActivity(id);
    }

    @PostMapping("/activity")
    public ActivityDTO addActivity(@RequestBody @Valid ActivityDTO activityDTO){
        return activityClient.addActivity(activityDTO);
    }

    @PutMapping("/activity/{id}")
    public ActivityDTO updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityDTO activityDTO){
        return activityClient.updateActivity(id, activityDTO);
    }

    @DeleteMapping("/activity/{id}")
    public void deleteActivity(@PathVariable Long id){
        activityClient.deleteActivity(id);
    }
}
