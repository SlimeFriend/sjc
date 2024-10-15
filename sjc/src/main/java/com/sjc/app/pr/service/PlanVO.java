package com.sjc.app.pr.service;

import lombok.Data;

@Data
public class PlanVO {
	private String planCode;	// 생산코드
	private String startDate;	// 시작 일자
	private String endDate;		// 종료 일자
	private String manager;		// 담당자
	private String comm;		// 비고
	private Integer status;		// 상태 
}
