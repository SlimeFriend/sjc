package com.sjc.app.sales.service;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {
	private String ordCode;
	private String ordStatus;
	private Date ordDate;
	private Date dlvReqDate;
	private String comm;
	private String cpCode;
	private String cpName;
}
