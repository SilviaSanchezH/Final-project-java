package com.example.activitiesserver.controller.client;

import com.example.activitiesserver.controller.dto.CenterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("center-service")
public interface CenterClient {

    @GetMapping("/center/{id}")
    CenterDTO getCenter(@PathVariable Long id);
}
