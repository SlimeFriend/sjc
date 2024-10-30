package com.sjc.app.sales.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PrdManagementVO {
	private String prdName;
	private String prdCode;
	private String lot;
	private Date md;
	private Date ed;
	private Integer inQuantity;
	private Integer outQuantity;
	private Integer stcQuantity;
	private Integer remainQuantity;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date inDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date outDate;
    
	private String inOutType;
	private String cpName;
}
