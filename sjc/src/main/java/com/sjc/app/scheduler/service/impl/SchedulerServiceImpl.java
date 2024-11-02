package com.sjc.app.scheduler.service.impl;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sjc.app.scheduler.service.SchedulerService;
@Service
public class SchedulerServiceImpl implements SchedulerService{
	
    private boolean isEnabled = false;

    @Scheduled(fixedDelay = 1000)
    public void scheduledTask() {
        if (isEnabled) {
            System.out.println("Scheduled log: " + LocalDateTime.now());
        }
    }    
    
    public void startScheduler() {
        isEnabled = true;
        System.out.println("--------");
        System.out.println("스케쥴 시작!");
        System.out.println("--------");
    }
    
    public void stopScheduler() {
        isEnabled = false;
        System.out.println("--------");
        System.out.println("스케쥴 종료!");
        System.out.println("--------");
    }

}
