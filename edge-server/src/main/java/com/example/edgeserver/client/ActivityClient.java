package com.example.edgeserver.client;

import com.example.edgeserver.controller.dto.ActivityDTO;
import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "activity-service")
public interface ActivityClient {
    @GetMapping("/activities")
    public List<ActivityDTO> getAllActivities();

    @GetMapping("/activity/{id}")
    public ActivityDTO getActivity(@PathVariable Long id);

    @GetMapping("/activities/center/{id}")
    public List<ActivityDTO> getActivitiesByCenter(@PathVariable Long id);

    @GetMapping("/activity/{id}/workers")
    public List<WorkerDTO> getWorkersByActivity(@PathVariable Long id);

    @GetMapping("/activity/{id}/clients")
    public List<ClientDTO> getClientsByActivity(@PathVariable Long id);

    @PostMapping("/activity")
    public ActivityDTO addActivity(@RequestBody @Valid ActivityDTO activityDTO);

    @PutMapping("/activity/{id}")
    public ActivityDTO updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityDTO activityDTO);

    @DeleteMapping("/activity/{id}")
    public void deleteActivity(@PathVariable Long id);
}
