package com.example.activitiesserver.service;

import com.example.activitiesserver.controller.client.CenterClient;
import com.example.activitiesserver.controller.client.UserClient;
import com.example.activitiesserver.controller.dto.ActivityDTO;
import com.example.activitiesserver.controller.dto.CenterDTO;
import com.example.activitiesserver.controller.dto.ClientDTO;
import com.example.activitiesserver.controller.dto.WorkerDTO;
import com.example.activitiesserver.enums.ActivityType;
import com.example.activitiesserver.model.Activity;
import com.example.activitiesserver.model.Client;
import com.example.activitiesserver.model.Worker;
import com.example.activitiesserver.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CenterClient centerClient;

    @Autowired
    private UserClient userClient;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByCenter(Long centerId) {
        return activityRepository.findActivitiesByCenter(centerId);
    }

    public Activity getActivity(Long id) {
        return activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
    }

    public List<WorkerDTO> getWorkersByActivity(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        List<Long> workerIds = new ArrayList<>();
        for(Worker worker: activity.getWorkers()) {
            workerIds.add(worker.getWorkerId());
        }
        return userClient.workersList(workerIds);
    }

    public List<ClientDTO> getClientsByActivity(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        List<Long> clientIds = new ArrayList<>();
        for(Client client: activity.getClients()) {
            clientIds.add(client.getClientId());
        }
        return userClient.clientsList(clientIds);
    }

    public Activity addActivity(ActivityDTO activityDTO) {
        try {
            CenterDTO center = centerClient.getCenter(activityDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            Activity activity = new Activity(activityDTO.getTitle(), activityDTO.getDescription(), ActivityType.valueOf(activityDTO.getType().toUpperCase()), activityDTO.getDate(), activityDTO.getTime(),
                    activityDTO.getCenter());
            return activityRepository.save(activity);
        }catch (ResponseStatusException exception) {
            throw exception;
        }
    }

    public Activity updateActivity(ActivityDTO activityDTO) {
        try {
            activityRepository.findById(activityDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
            CenterDTO center = centerClient.getCenter(activityDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            Activity activity = new Activity(activityDTO.getTitle(), activityDTO.getDescription(), ActivityType.valueOf(activityDTO.getType().toUpperCase()), activityDTO.getDate(), activityDTO.getTime(),
                    activityDTO.getCenter());
            return activityRepository.save(activity);
        }catch (ResponseStatusException exception) {
            throw exception;
        }
    }

    public void deleteActivity(Long id) {
        activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        activityRepository.deleteById(id);
    }

    public void addWorkerToActivity(Long activityId, Long workerId){
       Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
       List<Worker> workerList = activity.getWorkers();
       workerList.add(new Worker(workerId, activity));
       activity.setWorkers(workerList);
       activityRepository.save(activity);
    }

    public void addClientToActivity(Long activityId, Long clientId){
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        List<Client> clientList = activity.getClients();
        clientList.add(new Client(clientId, activity));
        activity.setClients(clientList);
        activityRepository.save(activity);
    }
}
