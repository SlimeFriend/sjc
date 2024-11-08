package com.sjc.app.sales.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderVO {
	private String ordCode;
	private String ordDetailCode;
	private String ordStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ordDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dlvReqDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date outDate;
    
    private String userName;
    private String manager;
	private String comm;
	private String cpCode;
	private String cpName;
	private Integer ordQuantity;
	private Integer lackQuantity;
}
