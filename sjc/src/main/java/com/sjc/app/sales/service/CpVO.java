package com.sjc.app.sales.service;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CpVO {
	private String cpCode;
	private String cpName;
	private String cpType;
	private String businessNo;
	private String address;
	private String comm;
	private String tel;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cpDate;
}
