package com.example.edgeserver.service;

import com.example.edgeserver.client.ActivityClient;
import com.example.edgeserver.controller.dto.ActivityDTO;
import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.UpdateActivityDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityClient activityClient;


    public List<ActivityDTO> getAllActivities(){
        return activityClient.getAllActivities();
    }


    public ActivityDTO getActivity(Long id){
        return activityClient.getActivity(id);
    }


    public List<ActivityDTO> getActivitiesByCenter(Long id){
        return activityClient.getActivitiesByCenter(id);
    }


    public List<WorkerDTO> getWorkersByActivity(Long id){
        return activityClient.getWorkersByActivity(id);
    }


    public List<ClientDTO> getClientsByActivity(Long id){
        return activityClient.getClientsByActivity(id);
    }


    public ActivityDTO addActivity(ActivityDTO activityDTO){
        return activityClient.addActivity(activityDTO);
    }


    public ActivityDTO updateActivity(Long id,  UpdateActivityDTO activityDTO){
        return activityClient.updateActivity(id, activityDTO);
    }


    public void deleteActivity(Long id){
        activityClient.deleteActivity(id);
    }

    public void addWorkerToActivity(Long activityId, Long workerId) {
        activityClient.addWorkerToActivity(activityId, workerId);
    }

    public void addClientToActivity(Long activityId, Long clientId) {
        activityClient.addClientToActivity(activityId, clientId);
    }
}
