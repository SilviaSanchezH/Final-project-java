package com.example.userserver.service;

import com.example.userserver.client.CenterClient;
import com.example.userserver.controller.dto.CenterDTO;
import com.example.userserver.controller.dto.WorkerDTO;
import com.example.userserver.model.Client;
import com.example.userserver.model.Worker;
import com.example.userserver.repository.WorkerRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private CenterClient centerClient;

    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }

    public Worker getWorker(Long id){
        return workerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker for that id doesn't exist"));
    }

    //    public Worker(String username, String password, String name, String lastName, String phoneNumber, String occupation, String professionalNumber, Long center) {
    public Worker addWorker(WorkerDTO workerDTO) {
        try {
            CenterDTO center = centerClient.getCenter(workerDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            Worker worker = new Worker(workerDTO.getUsername(), workerDTO.getPassword(), workerDTO.getName(), workerDTO.getLastName(), workerDTO.getPhoneNumber(),
                    workerDTO.getOccupation(), workerDTO.getProfessionalNumber(), workerDTO.getCenter());
            return workerRepository.save(worker);
        }catch (ResponseStatusException exception) {
            throw exception;
        }
    }

    public Worker updateWorker(WorkerDTO workerDTO){
        try {
            CenterDTO center = centerClient.getCenter(workerDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            workerRepository.findById(workerDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker for that id doesn't exist"));
            Worker worker = new Worker(workerDTO.getUsername(), workerDTO.getPassword(), workerDTO.getName(), workerDTO.getLastName(), workerDTO.getPhoneNumber(),
                    workerDTO.getOccupation(), workerDTO.getProfessionalNumber(), workerDTO.getCenter());
            return workerRepository.save(worker);
        }catch (ResponseStatusException exception) {
            throw exception;
        }
    }

    public void deleteWorker(Long id){
        workerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker for that id doesn't exist"));
        workerRepository.deleteById(id);
    }

    public List<Worker> workersByCenterId(Long id) {
       return workerRepository.findWorkersByCenter(id);
    }

    public List<Worker> getWorkersList(List<Long> idList) {
        return workerRepository.findAllById(idList);
    }
}
