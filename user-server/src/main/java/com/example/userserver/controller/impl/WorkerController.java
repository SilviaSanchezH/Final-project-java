package com.example.userserver.controller.impl;

import com.example.userserver.controller.dto.WorkerDTO;
import com.example.userserver.model.Client;
import com.example.userserver.model.Worker;
import com.example.userserver.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/workers")
    @ResponseStatus(HttpStatus.OK)
    public List<Worker> getAllWorkers(){
        return workerService.getAllWorkers();
    }

    @GetMapping("worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Worker getWorker(@PathVariable Long id){
        return workerService.getWorker(id);
    }

    @PostMapping("/worker")
    @ResponseStatus(HttpStatus.CREATED)
    public Worker addWorker(@RequestBody @Valid WorkerDTO worker){
        return workerService.addWorker(worker);
    }

    @PutMapping("/worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Worker updateWorker(@PathVariable Long id, @Valid @RequestBody WorkerDTO worker){
        worker.setId(id);
        return workerService.updateWorker(worker);
    }

    @DeleteMapping("/worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorker(@PathVariable Long id){
        workerService.deleteWorker(id);
    }

    @GetMapping("/workers/center/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Worker> workersByCenterId(@PathVariable Long id){
       return workerService.workersByCenterId(id);
    }

    @GetMapping("/workers/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Worker> workersList(@RequestBody List<Long> idList) {
        return workerService.getWorkersList(idList);
    }
}
