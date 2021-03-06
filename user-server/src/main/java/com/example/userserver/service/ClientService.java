package com.example.userserver.service;

import com.example.userserver.client.CenterClient;
import com.example.userserver.controller.dto.CenterDTO;
import com.example.userserver.controller.dto.ClientDTO;
import com.example.userserver.model.Client;
import com.example.userserver.repository.ClientRepository;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CenterClient centerClient;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClient(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client for that id doesn't exist"));
    }

    public Client addClient(ClientDTO clientDTO) {
       try {
           CenterDTO center = centerClient.getCenter(clientDTO.getCenter());
           if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
           Client client = new Client(clientDTO.getUsername(), clientDTO.getPassword(), clientDTO.getName(), clientDTO.getLastName(), clientDTO.getPhoneNumber(),
                   clientDTO.getAddress(),clientDTO.getCity(), clientDTO.getCenter());
           return clientRepository.save(client);
       }catch (ResponseStatusException exception) {
           throw exception;
       }
    }

    public Client updateClient(ClientDTO clientDTO){
        try {
            CenterDTO center = centerClient.getCenter(clientDTO.getCenter());
            if (center == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid center id");
            clientRepository.findById(clientDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client for that id doesn't exist"));
            Client client = new Client(clientDTO.getUsername(), clientDTO.getPassword(), clientDTO.getName(), clientDTO.getLastName(), clientDTO.getPhoneNumber(),
                    clientDTO.getAddress(),clientDTO.getCity(), clientDTO.getCenter());
            return clientRepository.save(client);
        }catch (ResponseStatusException exception) {
            throw exception;
        }
    }

    public void deleteClient(Long id){
        clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client for that id doesn't exist"));
        clientRepository.deleteById(id);
    }

    public List<Client> clientsByCenterId(Long id){
        return clientRepository.findClientsByCenter(id);
    }

    public List<Client> getClientsList(List<Long> idList) {
        return clientRepository.findAllById(idList);
    }

}
