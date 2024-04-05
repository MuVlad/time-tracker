package com.muslimov.vlad.timetracker.model;

import com.muslimov.vlad.timetracker.annotation.TrackAsyncTime;
import com.muslimov.vlad.timetracker.annotation.TrackTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Info {

    public Info() {
        log.info("Constructor Info");
    }

    @TrackAsyncTime
    public void method1() {
        try {
            Thread.sleep(1000);
            log.info("Message from Info.method1()");
        } catch (InterruptedException e) {
            log.info("Exception in Info method1");
        }
    }

    @TrackTime
    public void methods2() {
        try {
            Thread.sleep(200);
            log.info("Message from Info.method2()");
        } catch (InterruptedException e) {
            log.info("Exception in Info method2");
        }
    }
}
