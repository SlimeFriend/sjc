package com.sjc.app.scheduler.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sjc.app.info.mapper.InfoEqMapper;
import com.sjc.app.info.service.EqLogVO;
import com.sjc.app.scheduler.service.SchedulerService;
@Service
public class SchedulerServiceImpl implements SchedulerService{
	
    private boolean isEnabled = false;
    private InfoEqMapper infoEqMapper;
    
    @Autowired
    public SchedulerServiceImpl(InfoEqMapper infoEqMapper) {
    	this.infoEqMapper = infoEqMapper;
	}
    
    @Scheduled(fixedDelay = 1000)
    public void scheduledTask() {
        if (isEnabled) {
            System.out.println("Scheduled log: " + LocalDateTime.now());
            
    		EqLogVO moldEqLogVO = new EqLogVO();
    		EqLogVO mixEqLogVO = new EqLogVO();		
    		
    		// 140~145 사이의 랜덤 값 생성
    		double moldValue = Math.round((140 + Math.random() * 5) * 10.0) / 10.0;
    		double mixValue = Math.round((140 + Math.random() * 5) * 10.0) / 10.0;
    		
    		moldEqLogVO.setEqName("성형기");
    		mixEqLogVO.setEqName("배합기");            
            
    		moldEqLogVO.setEqLogData(moldValue);
    		mixEqLogVO.setEqLogData(mixValue);

            infoEqMapper.insertEqLog(moldEqLogVO);
            infoEqMapper.insertEqLog(mixEqLogVO);
            
            System.out.println("로그 저장 완료");       
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
