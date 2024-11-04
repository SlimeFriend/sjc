package com.sjc.app.eq.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EqChckVO {
	private String eqChckOpcd;  // 설비 가동 코드
	private String eqCode;		// 설비 코드
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eqNextChck;	// 다음 점검일
	private String eqChckOx;	// 점검 유무
	private Date startDate;		// 시작 일자
	private Date endDate;		// 종료 일자
	private String reason;		// 사유
	private String comm;		// 비고
	
	// 조인 정보 받아온 속성
	private String eqName;		  // 설비명
}
