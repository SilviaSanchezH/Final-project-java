package com.example.activitiesserver.service;

import com.example.activitiesserver.controller.client.CenterClient;
import com.example.activitiesserver.controller.client.UserClient;
import com.example.activitiesserver.controller.dto.ActivityDTO;
import com.example.activitiesserver.controller.dto.CenterDTO;
import com.example.activitiesserver.controller.dto.ClientDTO;
import com.example.activitiesserver.controller.dto.WorkerDTO;
import com.example.activitiesserver.enums.ActivityType;
import com.example.activitiesserver.model.Activity;
import com.example.activitiesserver.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

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
        return userClient.workersList(activity.getWorkers());
    }

    public List<ClientDTO> getClientsByActivity(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        return userClient.clientsList(activity.getUsers());
    }

    public Activity addActivity(ActivityDTO activityDTO) {
        try {
            CenterDTO center = centerClient.getCenter(activityDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            Activity activity = new Activity(activityDTO.getTitle(), activityDTO.getDescription(), ActivityType.valueOf(activityDTO.getType().toUpperCase()), activityDTO.getDate(), activityDTO.getTime(),
                    activityDTO.getCenter(), activityDTO.getUsers(), activityDTO.getWorkers());
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
                    activityDTO.getCenter(), activityDTO.getUsers(), activityDTO.getWorkers());
            return activityRepository.save(activity);
        }catch (ResponseStatusException exception) {
            throw exception;
        }
    }

    public void deleteActivity(Long id) {
        activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity for that id doesn't exist"));
        activityRepository.deleteById(id);
    }
}
