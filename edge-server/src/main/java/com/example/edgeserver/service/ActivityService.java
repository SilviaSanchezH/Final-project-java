package com.example.edgeserver.service;

import com.example.edgeserver.client.ActivityClient;
import com.example.edgeserver.controller.dto.ActivityDTO;
import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.UpdateActivityDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityClient activityClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<ActivityDTO> getAllActivities(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("activities-service");
        List<ActivityDTO> activityDTOList = circuitBreaker.run(()-> activityClient.getAllActivities(), throwable -> activitiesFallBack());
        if(activityDTOList == null){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
        return activityDTOList;
    }


    public ActivityDTO getActivity(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("activities-service");
        ActivityDTO activityDTO = circuitBreaker.run(()-> activityClient.getActivity(id), throwable -> activityFallBack());
        if(activityDTO == null){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
        return activityDTO;
    }


    public List<ActivityDTO> getActivitiesByCenter(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("activities-service");
        List<ActivityDTO> activityDTOList = circuitBreaker.run(()-> activityClient.getActivitiesByCenter(id), throwable -> activitiesFallBack());
        if(activityDTOList == null){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
        return activityDTOList;
    }


    public List<WorkerDTO> getWorkersByActivity(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("activities-service");
        List<WorkerDTO> workerDTOList = circuitBreaker.run(()-> activityClient.getWorkersByActivity(id), throwable -> activityWorkersFallBack());
        if(workerDTOList == null){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
        return workerDTOList;
    }


    public List<ClientDTO> getClientsByActivity(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("activities-service");
        List<ClientDTO> clientDTOList = circuitBreaker.run(()-> activityClient.getClientsByActivity(id), throwable -> activityClientsFallBack());
        if(clientDTOList == null){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
        return clientDTOList;

    }


    public ActivityDTO addActivity(ActivityDTO activityDTO){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("activities-service");
        ActivityDTO activityDTO1 = circuitBreaker.run(()-> activityClient.addActivity(activityDTO), throwable -> activityFallBack());
        if(activityDTO1 == null){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
        return activityDTO1;
    }

    public ActivityDTO updateActivity(Long id,  UpdateActivityDTO activityDTO){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("activities-service");
        ActivityDTO activityDTO1 = circuitBreaker.run(()-> activityClient.updateActivity(id, activityDTO), throwable -> activityFallBack());
        if(activityDTO1 == null){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
        return activityDTO1;
    }


    public void deleteActivity(Long id){
        try {
            activityClient.deleteActivity(id);
        }catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
    }

    public void addWorkerToActivity(Long activityId, Long workerId) {
        try {
            activityClient.addWorkerToActivity(activityId, workerId);
        }catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
    }

    public void addClientToActivity(Long activityId, Long clientId) {
        try{
            activityClient.addClientToActivity(activityId, clientId);
        }catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activity server not available");
        }
    }

    private List <ActivityDTO> activitiesFallBack() {
        return null;
    }

    private ActivityDTO activityFallBack() {
        return null;
    }

    private List<WorkerDTO> activityWorkersFallBack() {
        return null;
    }

    private List<ClientDTO> activityClientsFallBack() {
        return null;
    }
}
