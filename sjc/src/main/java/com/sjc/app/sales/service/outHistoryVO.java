package com.sjc.app.sales.service;

import java.util.Date;

import lombok.Data;

@Data
public class outHistoryVO {
	private Integer outNo;
	private String lot;
	private String prdCode;
	private String prdName;
	private Integer outQuantity;
	private Date outDate;
	private String cpName;
	private String cpCode;
	private String ordCode;
}
