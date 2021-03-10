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
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CenterClient centerClient;

    @Autowired
    private UserClient userClient;

    public List<ActivityDTO> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        List<ActivityDTO> activityDTOList = new ArrayList<ActivityDTO>();
        for(Activity activity: activities) {
            List<Long> clients = activity.getClients().stream().map(Client::getClientId).collect(Collectors.toList());
            List<Long> workers = activity.getWorkers().stream().map(Worker::getWorkerId).collect(Collectors.toList());
            activityDTOList.add(new ActivityDTO(activity.getId(), activity.getTitle(), activity.getDescription(), activity.getType().toString(),
                    activity.getDate(), activity.getTime(), activity.getCenter(), clients, workers));
        }
        return activityDTOList;
    }

    public List<ActivityDTO> getActivitiesByCenter(Long centerId) {
        List<Activity> activities = activityRepository.findActivitiesByCenter(centerId);
        List<ActivityDTO> activityDTOList = new ArrayList<ActivityDTO>();
        for(Activity activity: activities) {
            List<Long> clients = activity.getClients().stream().map(Client::getClientId).collect(Collectors.toList());
            List<Long> workers = activity.getWorkers().stream().map(Worker::getWorkerId).collect(Collectors.toList());
            activityDTOList.add(new ActivityDTO(activity.getId(), activity.getTitle(), activity.getDescription(), activity.getType().toString(),
                    activity.getDate(), activity.getTime(), activity.getCenter(), clients, workers));
        }
        return activityDTOList;
    }

    public ActivityDTO getActivity(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        List<Long> clients = activity.getClients().stream().map(Client::getClientId).collect(Collectors.toList());
        List<Long> workers = activity.getWorkers().stream().map(Worker::getWorkerId).collect(Collectors.toList());
        return new ActivityDTO(activity.getId(), activity.getTitle(), activity.getDescription(), activity.getType().toString(),
                activity.getDate(), activity.getTime(), activity.getCenter(), clients, workers);
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

    public ActivityDTO updateActivity(ActivityDTO activityDTO) {
        try {
            Activity activity = activityRepository.findById(activityDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
            CenterDTO center = centerClient.getCenter(activityDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            Activity updatedActivity = new Activity(activityDTO.getId(), activityDTO.getTitle(), activityDTO.getDescription(), ActivityType.valueOf(activityDTO.getType().toUpperCase()), activityDTO.getDate(), activityDTO.getTime(),
                    activityDTO.getCenter());
            updatedActivity.setWorkers(activity.getWorkers());
            updatedActivity.setClients(activity.getClients());
            activityRepository.save(updatedActivity);
            return activityDTO;
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
       List<Activity> activities = new ArrayList<>();
       activities.add(activity);
       workerList.add(new Worker(workerId, activities));
       activity.setWorkers(workerList);
       activityRepository.save(activity);
    }

    public void addClientToActivity(Long activityId, Long clientId){
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        List<Client> clientList = activity.getClients();
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);
        clientList.add(new Client(clientId, activities));
        activity.setClients(clientList);
        activityRepository.save(activity);
    }
}
