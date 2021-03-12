package com.example.edgeserver.service;

import com.example.edgeserver.client.UserClient;
import com.example.edgeserver.controller.dto.*;
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
public class UserService {
    @Autowired
    private UserClient userClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();


    public List<ClientDTO> getAllClients() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        List<ClientDTO> clientDTOList = circuitBreaker.run(()-> userClient.getAllClients(), throwable -> clientsFallBack());
        if (clientDTOList == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }

        return clientDTOList;
    }

    public ClientDTO getClient(Long id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        ClientDTO clientDTO = circuitBreaker.run(()-> userClient.getClient(id), throwable -> clientFallBack());
        if (clientDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return clientDTO;
    }

    public ClientDTO addClient(NewClientDTO client) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        ClientDTO clientDTO = circuitBreaker.run(()-> userClient.addClient(client), throwable -> clientFallBack());
        if (clientDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return clientDTO;
    }

    public ClientDTO updateClient(Long id, UpdateClientDTO client){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        ClientDTO clientDTO = circuitBreaker.run(()-> userClient.updateClient(id,client), throwable -> clientFallBack());
        if (clientDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return clientDTO;
    }

    public void deleteClient(Long id){
        try{
            userClient.deleteClient(id);
        }catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
    }

    public List<ClientDTO> clientsByCenterId(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        List<ClientDTO> clientDTOList = circuitBreaker.run(()-> userClient.clientsByCenterId(id), throwable -> clientsFallBack());
        if (clientDTOList == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return clientDTOList;
    }

    public List<ClientDTO> clientsList(List<Long> idList){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        List<ClientDTO> clientDTOList = circuitBreaker.run(()-> userClient.clientsList(idList), throwable -> clientsFallBack());
        if (clientDTOList == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return clientDTOList;
    }

    public List<WorkerDTO> getAllWorkers(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        List<WorkerDTO> workerDTOList = circuitBreaker.run(()-> userClient.getAllWorkers(), throwable -> workersFallBack());
        if (workerDTOList == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return workerDTOList;
    }

    public WorkerDTO getWorker(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        WorkerDTO workerDTO = circuitBreaker.run(()-> userClient.getWorker(id), throwable -> workerFallBack());
        if (workerDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return workerDTO;
    }

    public WorkerDTO addWorker(NewWorkerDTO worker){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        WorkerDTO workerDTO = circuitBreaker.run(()-> userClient.addWorker(worker), throwable -> workerFallBack());
        if (workerDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return workerDTO;
    }

    public WorkerDTO updateWorker(Long id, UpdateWorkerDTO worker){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        WorkerDTO workerDTO = circuitBreaker.run(()-> userClient.updateWorker(id, worker), throwable -> workerFallBack());
        if (workerDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return workerDTO;
    }

    public void deleteWorker(Long id){
        try{
            userClient.deleteWorker(id);
        }catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
    }

    public List<WorkerDTO> workersByCenterId(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        List<WorkerDTO> workerDTOList = circuitBreaker.run(()-> userClient.workersByCenterId(id), throwable -> workersFallBack());
        if (workerDTOList == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return workerDTOList;
    }

    public List<WorkerDTO> workersList(List<Long> idList){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        List<WorkerDTO> workerDTOList = circuitBreaker.run(()-> userClient.workersList(idList), throwable -> workersFallBack());
        if (workerDTOList == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return workerDTOList;
    }

    public UserDTO getUser(String username){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
        UserDTO userDTO = circuitBreaker.run(()-> userClient.getUser(username), throwable -> userFallBack());
        if (userDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User server not available");
        }
        return userDTO;
    }

    private List<ClientDTO> clientsFallBack() {
        return null;
    }

    private ClientDTO clientFallBack() {
        return null;
    }

    private List<WorkerDTO> workersFallBack() {
        return null;
    }

    private WorkerDTO workerFallBack() {
        return null;
    }

    private UserDTO userFallBack() {
        return null;
    }
}
