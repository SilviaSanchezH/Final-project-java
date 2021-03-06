package com.example.centerserver.service;

import com.example.centerserver.model.Center;
import com.example.centerserver.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    public List<Center> getAllCenters(){
        return centerRepository.findAll();
    }

    public Center getCenter(Long id){
        return centerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Center for that id doesn't exist"));
    }


}
