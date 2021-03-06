package com.example.centerserver.controller.impl;

import com.example.centerserver.model.Center;
import com.example.centerserver.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CenterController {

    @Autowired
    private CenterService centerService;

    @GetMapping("/centers")
    @ResponseStatus(HttpStatus.OK)
    public List<Center> getAllCenters(){
        return centerService.getAllCenters();
    }

    @GetMapping("/center/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Center getCenter(@PathVariable Long id){
        return centerService.getCenter(id);
    }



}
