package com.sjc.app.sales.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class outHistoryVO {
	private Integer outNo;
	private String lot;
	private String prdCode;
	private String prdName;
	private Integer outQuantity;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date outDate;
    
    private String userName;
    private String manager;
	private String cpName;
	private String cpCode;
	private String ordCode;
}
