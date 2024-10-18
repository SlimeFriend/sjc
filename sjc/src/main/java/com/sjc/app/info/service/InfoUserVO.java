package com.sjc.app.info.service;

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
	
	private String perPage;
	private String page;
}
