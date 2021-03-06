package com.example.edgeserver.client;

import com.example.edgeserver.controller.dto.CenterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "center-service")
public interface CenterClient {
    @GetMapping("/centers")
    public List<CenterDTO> getAllCenters();

    @GetMapping("/center/{id}")
    public CenterDTO getCenter(@PathVariable Long id);
}
