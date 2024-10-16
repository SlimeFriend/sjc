package com.sjc.app.info.service;

import java.util.Date;

import lombok.Data;

@Data
public class BomVO {
	private String bomCode;		//BOM코드
	private String prdCode;		//제품코드
	private String description;	//설명
	private Date regDate;		//등록일자
	private String manager;		//담당자
	private String comm;		//비고
	
	//BDetailVO
	private String bDetailCode;			//BOM상세코드
	private Integer quantityRequired;	//소요수량
//	private String comm;				//비고
	private String mtCode;				//자재코드
	private String no;					//순서
	
}
