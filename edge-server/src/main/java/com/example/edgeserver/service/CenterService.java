package com.example.edgeserver.service;

import com.example.edgeserver.client.CenterClient;
import com.example.edgeserver.controller.dto.CenterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CenterService {

    @Autowired
    private CenterClient centerClient;

    public List<CenterDTO> getAllCenters(){
        return centerClient.getAllCenters();
    }

    public CenterDTO getCenter(Long id){
        return centerClient.getCenter(id);
    }
}
