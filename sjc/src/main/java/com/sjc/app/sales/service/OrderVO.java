package com.sjc.app.sales.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OrderVO {
	private String ordCode;
	private String ordDetailCode;
	private String ordStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ordDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dlvReqDate;
	
	private String comm;
	private String cpCode;
	private String cpName;
	private Integer ordQuantity;
}
