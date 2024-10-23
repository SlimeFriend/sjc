package com.sjc.app.sales.service;

import java.util.Date;

import lombok.Data;

@Data
public class PrdManagementVO {
	private String prdCode;
	private String lot;
	private Date md;
	private Date ed;
	private Integer inQuantity;
	private Integer outQuantity;
	private Integer stcQuantity;
	private Date inDate;
	private String inOutType;
}
