package com.sjc.app.pr.service;

import lombok.Data;

@Data
public class PResultVO {	
	private String presultCode;		// 생산공정실적 코드
	private String prdCode;			// 제품 코드
	private String startTime;		// 시작 시간
	private String endTime;			// 종료 시간
	private Integer output;			// 생산량
	private Integer err;			// 불량량
	private String processDate;		// 공정 일자
	private String eqCode;			// 설비 이름
	private String processName;		// 공정 이름
	private String manager;			// 담당자
	private String pdetailCode;		// 생산지시상세 코드
	private String ldetailCode;		// 라인상세정보 코드
	private Integer input;			// 투입량
	private String status;			// 상태
	private Integer no;				// 순서
}		
