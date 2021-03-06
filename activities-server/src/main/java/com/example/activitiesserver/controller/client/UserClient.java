package com.example.activitiesserver.controller.client;

import com.example.activitiesserver.controller.dto.ClientDTO;
import com.example.activitiesserver.controller.dto.WorkerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/workers/list")
    public List<WorkerDTO> workersList(@RequestBody List<Long> idList);
    @GetMapping("/clients/list")
    public List<ClientDTO> clientsList(@RequestBody List<Long> idList);
}
