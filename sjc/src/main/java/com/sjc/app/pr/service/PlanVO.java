package com.sjc.app.pr.service;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PlanVO {

    private String planCode;    // 생산코드
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;    // 시작 일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;        // 종료 일자
    private String manager;        // 담당자
    private String comm;        // 비고
    private String status;        // 상태 
    private String userName;	// 이름
    private String ordCode; 	// 주문코드
    
    private List<PlanDVO> planDetails; // 생산계획 상세 리스트
   
}

