package com.example.userserver.service;

import com.example.userserver.client.CenterClient;
import com.example.userserver.controller.dto.CenterDTO;
import com.example.userserver.controller.dto.UpdateWorkerDTO;
import com.example.userserver.controller.dto.WorkerDTO;
import com.example.userserver.enums.Gender;
import com.example.userserver.enums.RoleEnum;
import com.example.userserver.model.Role;
import com.example.userserver.model.Worker;
import com.example.userserver.repository.RoleRepository;
import com.example.userserver.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CenterClient centerClient;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }

    public Worker getWorker(Long id){
        return workerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker for that id doesn't exist"));
    }

    public Worker addWorker(WorkerDTO workerDTO) {
        try {
            CenterDTO center = centerClient.getCenter(workerDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            String password = passwordEncoder.encode(workerDTO.getPassword());
            Worker worker = new Worker(workerDTO.getUsername(), password, workerDTO.getName(), workerDTO.getLastName(), workerDTO.getSecondSurname(), workerDTO.getPhoneNumber(),
                    workerDTO.getOccupation(), workerDTO.getProfessionalNumber(), workerDTO.getCenter(), Gender.valueOf(workerDTO.getGender().toUpperCase()));
            worker.setRole(new Role(RoleEnum.WORKER, worker));
            return workerRepository.save(worker);
        }catch (ResponseStatusException exception) {
            throw exception;
        }
    }

    public Worker updateWorker(UpdateWorkerDTO workerDTO){
        try {
            CenterDTO center = centerClient.getCenter(workerDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            Worker beforeWorker = workerRepository.findById(workerDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker for that id doesn't exist"));
            Worker worker = new Worker(workerDTO.getId(), workerDTO.getUsername(), beforeWorker.getPassword(), workerDTO.getName(), workerDTO.getLastName(), workerDTO.getSecondSurname(), workerDTO.getPhoneNumber(),
                    workerDTO.getOccupation(), workerDTO.getProfessionalNumber(), workerDTO.getCenter(), Gender.valueOf(workerDTO.getGender().toUpperCase()));
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
