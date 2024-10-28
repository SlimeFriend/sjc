package com.sjc.app.info.service;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.sjc.app.sales.service.ProductVO;

import lombok.Data;

@Data
public class BomVO extends ProductVO{
	private String bomCode;		//BOM코드
	private String prdCode;		//제품코드
	private String description;	//설명
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date regDate;		//등록일자
	
//	@JsonFormat(pattern = "yyyy-MM-dd")
//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//	private LocalDateTime regDate;
	
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDate;
	
	private String manager;		//담당자
	private String comm;		//비고
	
	//BDetailVO
	private String bDetailCode;			//BOM상세코드
	private Integer quantityRequired;	//소요수량
//	private String comm;				//비고
	private String mtCode;				//자재코드
	private String no;					//순서
	
}
