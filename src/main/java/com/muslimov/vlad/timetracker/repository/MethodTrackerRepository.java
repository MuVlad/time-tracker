package com.muslimov.vlad.timetracker.repository;

import com.muslimov.vlad.timetracker.model.MethodTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MethodTrackerRepository extends JpaRepository<MethodTracker, Long> {

    List<MethodTracker> findByMethodName(String methodName);
}
