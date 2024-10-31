package com.sjc.app.sales.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProductVO {
	private String prdCode;
	private String prdName;
	private String description;
	private String comm;
	private String lot;
	private Integer inQuantity;
	private Integer outQuantity;
	private Integer stcQuantity;
	private Integer unitPrice;
	private Integer ordQuantity;
	private Integer price;
	private String bomCode;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date md;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date ed;
    
}
