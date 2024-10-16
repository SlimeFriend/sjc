package com.sjc.app.sales.service;

import lombok.Data;

@Data
public class PrdVO {
	private String prdCode;
	private String prdName;
	private Integer unitPrice;
	private String description;
	private String comm;
}
