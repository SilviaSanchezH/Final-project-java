package com.example.userserver.controller.impl;

import com.example.userserver.controller.dto.ClientDTO;
import com.example.userserver.controller.dto.UpdateClientDTO;
import com.example.userserver.model.Client;
import com.example.userserver.model.Worker;
import com.example.userserver.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@Valid @RequestBody ClientDTO client){
        return clientService.addClient(client);
    }

    @PutMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@PathVariable Long id, @Valid @RequestBody UpdateClientDTO client){
        client.setId(id);
        return clientService.updateClient(client);
    }

    @DeleteMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @GetMapping("/clients/center/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> clientsByCenterId(@PathVariable Long id){
        return clientService.clientsByCenterId(id);
    }

    @PostMapping("/clients/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> clientsList(@RequestBody List<Long> idList) {
        return clientService.getClientsList(idList);
    }

}
