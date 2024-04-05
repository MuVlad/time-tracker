package com.muslimov.vlad.timetracker.controller;

import com.muslimov.vlad.timetracker.dto.ResponseMessage;
import com.muslimov.vlad.timetracker.service.MethodTrackerStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistics")
@Tag(name = "MethodTrackerStatisticsController", description = "Контроллер учета времени выполнения методов")
public class MethodTrackerStatisticsController {

    private final MethodTrackerStatisticsService methodTrackerStatisticsService;

    @GetMapping("/average")
    @Operation(summary = "Получение среднего времени выполнения всех методов")
    public HttpEntity<ResponseMessage> getAverageAllMethodTime() {

        final var averageExecutionTime = methodTrackerStatisticsService.getAverageExecutionTime();
        return ResponseEntity.ok(new ResponseMessage("Среднее время выполения всех методов : " + averageExecutionTime + "мс"));
    }

    @GetMapping("/average/{methodName}")
    @Operation(summary = "Получение среднего времени выполнения конкретного метода")
    public HttpEntity<ResponseMessage> getAverageMethodTime(@PathVariable String methodName) {

        final var averageExecutionTime = methodTrackerStatisticsService.getAverageExecutionTime(methodName);
        return ResponseEntity.ok(new ResponseMessage("Среднее время выполения методов " + methodName + " : " + averageExecutionTime + "мс"));
    }

    @GetMapping("/total")
    @Operation(summary = "Получение общего времени выполнения всех методов")
    public HttpEntity<ResponseMessage> getTotalAllMethodTime() {

        final var totalExecutionTime = methodTrackerStatisticsService.getTotalExecutionTime();
        return ResponseEntity.ok(new ResponseMessage("Общее время выполения всех методов : " + totalExecutionTime + "мс"));
    }

    @GetMapping("/total/{methodName}")
    @Operation(summary = "Получение общего времени выполнения конкретного метода")
    public HttpEntity<ResponseMessage> getTotalMethodTime(@PathVariable String methodName) {

        final var totalExecutionTime = methodTrackerStatisticsService.getTotalExecutionTime(methodName);
        return ResponseEntity.ok(new ResponseMessage("Общее время выполения методов " + methodName + " : " + totalExecutionTime + "мс"));
    }
}
