package com.example.activitiesserver.repository;

import com.example.activitiesserver.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    public List<Activity> findActivitiesByCenter(Long centerId);
}
