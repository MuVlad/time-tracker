package com.muslimov.vlad.timetracker.service;

import com.muslimov.vlad.timetracker.exception.model.NotFoundException;
import com.muslimov.vlad.timetracker.model.MethodTracker;
import com.muslimov.vlad.timetracker.repository.MethodTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MethodTrackerStatisticsService {

    private final MethodTrackerRepository methodTrackerRepository;

    public Double getAverageExecutionTime(String methodName) {

        List<MethodTracker> methods = methodTrackerRepository.findByMethodName(methodName);

        if (methods.isEmpty()) {
            throw new NotFoundException("methods with name: " + methodName + " not found");
        }

        return methods.stream()
            .mapToLong(MethodTracker::getExecutionTime)
            .average()
            .orElse(0);
    }

    public Double getAverageExecutionTime() {

        List<MethodTracker> methods = methodTrackerRepository.findAll();

        return methods.stream()
            .mapToLong(MethodTracker::getExecutionTime)
            .average()
            .orElse(0);
    }

    public Long getTotalExecutionTime(String methodName) {

        List<MethodTracker> methods = methodTrackerRepository.findByMethodName(methodName);

        if (methods.isEmpty()) {
            throw new NotFoundException("methods with name: " + methodName + " not found");
        }

        return methods.stream()
            .mapToLong(MethodTracker::getExecutionTime)
            .sum();
    }

    public Long getTotalExecutionTime() {

        List<MethodTracker> methods = methodTrackerRepository.findAll();

        return methods.stream()
            .mapToLong(MethodTracker::getExecutionTime)
            .sum();
    }
}