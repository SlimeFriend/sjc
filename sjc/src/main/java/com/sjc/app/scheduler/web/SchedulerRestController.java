package com.sjc.app.scheduler.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.scheduler.service.SchedulerService;

@RestController
public class SchedulerRestController {
	    
    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/startScheduler")
    public String startScheduler() {
        schedulerService.startScheduler();
        return "Scheduler started";
    }

    @PostMapping("/stopScheduler")
    public String stopScheduler() {
        schedulerService.stopScheduler();
        return "Scheduler stopped";
    }

}
