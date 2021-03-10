package com.example.activitiesserver.controller.client;

import com.example.activitiesserver.controller.dto.ClientDTO;
import com.example.activitiesserver.controller.dto.WorkerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {
    @PostMapping("/workers/list")
    public List<WorkerDTO> workersList(@RequestBody List<Long> idList);
    @PostMapping("/clients/list")
    public List<ClientDTO> clientsList(@RequestBody List<Long> idList);
}
