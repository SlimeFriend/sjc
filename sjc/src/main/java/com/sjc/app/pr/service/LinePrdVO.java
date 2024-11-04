package com.sjc.app.pr.service;

import lombok.Data;

@Data
public class LinePrdVO {
	private String prdCode;				// 제품코드
	private String prdName;				// 제품이름
	private String lineCode;            // 라인코드
	private String use;					// 라인 사용가능 상태
	private Integer want;				// 목표량
	private Integer command;			// 지시량
}
