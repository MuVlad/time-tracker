package com.muslimov.vlad.timetracker.service;

import com.muslimov.vlad.timetracker.model.MethodTracker;
import com.muslimov.vlad.timetracker.repository.MethodTrackerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MethodTrackerService {

    private final MethodTrackerRepository methodTrackerRepository;

    @Transactional
    public void saveMethodTracker(MethodTracker methodTracker) {
        System.out.println("saveMethodTracker in thread " + Thread.currentThread().getName());
        methodTrackerRepository.save(methodTracker);
    }
}
