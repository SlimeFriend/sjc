package com.sjc.app.pr.service;

import java.util.Date;

import lombok.Data;

@Data
public class PlanVO {
	private String planCode;	// 생산코드
	private Date startDate;	// 시작 일자
	private Date endDate;		// 종료 일자
	private String manager;		// 담당자
	private String comm;		// 비고
	private Integer status;		// 상태 
}
