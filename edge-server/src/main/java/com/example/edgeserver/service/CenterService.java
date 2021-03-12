package com.example.edgeserver.service;

import com.example.edgeserver.client.CenterClient;
import com.example.edgeserver.controller.dto.CenterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CenterService {

    @Autowired
    private CenterClient centerClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<CenterDTO> getAllCenters(){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("center-service");
        List<CenterDTO> centerDTOS = circuitBreaker.run(()-> centerClient.getAllCenters(), throwable -> centersFallBack());
        if (centerDTOS == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Center server not available");
        }
        return centerDTOS;
    }

    public CenterDTO getCenter(Long id){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("center-service");
        CenterDTO centerDTO = circuitBreaker.run(()-> centerClient.getCenter(id), throwable -> centerFallBack());
        if (centerDTO == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Center server not available");
        }
        return centerDTO;
    }

    private List<CenterDTO> centersFallBack() {
        return null;
    }

    private CenterDTO centerFallBack() {
        return null;
    }
}
