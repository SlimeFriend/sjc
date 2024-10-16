package com.sjc.app.sales.service;

import java.util.Date;

import lombok.Data;

@Data
public class ProductVO {
	private String prdCode;
	private String prdName;
	private String description;
	private String comm;
	private String lot;
	private Date md;
	private Date ed;
	private Integer inQuantity;
	private Integer outQuantity;
	private Integer stcQuantity;
	private Integer unitPrice;
}
