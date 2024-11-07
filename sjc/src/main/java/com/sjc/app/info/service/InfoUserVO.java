package com.sjc.app.info.service;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InfoUserVO {
	private String userId;
	private String loginId;
	private String password;
	private String roleName;
	private String userName;
	private String deptCode;
	private String deptName;
	private String phone;
	
	private int perPage;
	private int page;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	
}
