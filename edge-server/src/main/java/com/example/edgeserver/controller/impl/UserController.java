package com.example.edgeserver.controller.impl;

import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.UserDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import com.example.edgeserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable String username){
        return userService.getUser(username);
    }

    @GetMapping("/workers")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkerDTO> getAllWorkers(){
        return userService.getAllWorkers();
    }

    @GetMapping("worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkerDTO getWorker(@PathVariable Long id){
        return userService.getWorker(id);
    }

    @PostMapping("/worker")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkerDTO addWorker(@RequestBody @Valid WorkerDTO worker){
        return userService.addWorker(worker);
    }

    @PutMapping("/worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkerDTO updateWorker(@PathVariable Long id, @Valid @RequestBody WorkerDTO worker){
        return userService.updateWorker(id, worker);
    }

    @DeleteMapping("/worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorker(@PathVariable Long id){
        userService.deleteWorker(id);
    }

    @GetMapping("/workers/center/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkerDTO> workersByCenterId(@PathVariable Long id){
        return userService.workersByCenterId(id);
    }

    @GetMapping("/workers/list")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkerDTO> workersList(@RequestBody List<Long> idList) {
        return userService.workersList(idList);
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients(){
        return userService.getAllClients();
    }

    @GetMapping("client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClient(@PathVariable Long id){
        return userService.getClient(id);
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO addClient(@Valid @RequestBody ClientDTO client){
        return userService.addClient(client);
    }

    @PutMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO client){
        return userService.updateClient(id, client);
    }

    @DeleteMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long id){
        userService.deleteClient(id);
    }

    @GetMapping("/clients/center/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> clientsByCenterId(@PathVariable Long id){
        return userService.clientsByCenterId(id);
    }

    @GetMapping("/clients/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> clientsList(@RequestBody List<Long> idList) {
        return userService.clientsList(idList);
    }

}
