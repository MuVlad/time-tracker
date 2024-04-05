package com.muslimov.vlad.timetracker;

import com.muslimov.vlad.timetracker.model.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
public class TimeTrackerApplication {

    private final Info info;

    public static void main(String[] args) {
        SpringApplication.run(TimeTrackerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        info.method1();
        info.method1();
        info.method1();
        info.methods2();
        info.methods2();
    }
}
