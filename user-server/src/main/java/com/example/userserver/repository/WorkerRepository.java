package com.example.userserver.repository;

import com.example.userserver.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    public List<Worker> findWorkersByCenter(Long id);
}
