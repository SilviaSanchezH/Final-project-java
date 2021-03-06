package com.example.edgeserver.service;

import com.example.edgeserver.client.UserClient;
import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.UserDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserClient userClient;


    public List<ClientDTO> getAllClients() {
        return userClient.getAllClients();
    }

    public ClientDTO getClient(Long id) {
        return userClient.getClient(id);
    }

    public ClientDTO addClient(ClientDTO client) {
        return userClient.addClient(client);
    }

    public ClientDTO updateClient(Long id, ClientDTO client){
        return userClient.updateClient(id, client);
    }

    public void deleteClient(Long id){
        userClient.deleteClient(id);
    }

    public List<ClientDTO> clientsByCenterId(Long id){
        return userClient.clientsByCenterId(id);
    }

    public List<ClientDTO> clientsList(List<Long> idList){
        return userClient.clientsList(idList);
    }


    public List<WorkerDTO> getAllWorkers(){
        return userClient.getAllWorkers();
    }

    public WorkerDTO getWorker(Long id){
        return userClient.getWorker(id);
    }

    public WorkerDTO addWorker(WorkerDTO worker){
        return userClient.addWorker(worker);
    }

    public WorkerDTO updateWorker(Long id, WorkerDTO worker){
        return userClient.updateWorker(id, worker);
    }

    public void deleteWorker(Long id){
        userClient.deleteWorker(id);
    }

    public List<WorkerDTO> workersByCenterId(Long id){
        return userClient.workersByCenterId(id);
    }

    public List<WorkerDTO> workersList(List<Long> idList){
        return userClient.workersList(idList);
    }


    public UserDTO getUser(String username){
        return userClient.getUser(username);
    }
}
