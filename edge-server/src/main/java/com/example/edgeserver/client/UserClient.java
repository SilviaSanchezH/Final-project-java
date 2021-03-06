package com.example.edgeserver.client;

import com.example.edgeserver.controller.dto.ClientDTO;
import com.example.edgeserver.controller.dto.UserDTO;
import com.example.edgeserver.controller.dto.WorkerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/clients")
    public List<ClientDTO> getAllClients();
    @GetMapping("client/{id}")
    public ClientDTO getClient(@PathVariable Long id);
    @PostMapping("/client")
    public ClientDTO addClient(@Valid @RequestBody ClientDTO client);
    @PutMapping("/client/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO client);
    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable Long id);
    @GetMapping("/clients/center/{id}")
    public List<ClientDTO> clientsByCenterId(@PathVariable Long id);
    @GetMapping("/clients/list")
    public List<ClientDTO> clientsList(@RequestBody List<Long> idList);


    @GetMapping("/workers")
    public List<WorkerDTO> getAllWorkers();
    @GetMapping("worker/{id}")
    public WorkerDTO getWorker(@PathVariable Long id);
    @PostMapping("/worker")
    public WorkerDTO addWorker(@RequestBody @Valid WorkerDTO worker);
    @PutMapping("/worker/{id}")
    public WorkerDTO updateWorker(@PathVariable Long id, @Valid @RequestBody WorkerDTO worker);
    @DeleteMapping("/worker/{id}")
    public void deleteWorker(@PathVariable Long id);
    @GetMapping("/workers/center/{id}")
    public List<WorkerDTO> workersByCenterId(@PathVariable Long id);
    @GetMapping("/workers/list")
    public List<WorkerDTO> workersList(@RequestBody List<Long> idList);


    @GetMapping("/user/{username}")
    public UserDTO getUser(@PathVariable String username);
}
