package com.sjc.app.info.service;

import lombok.Data;

@Data
public class ProcessVO {
	private String processCode;	//공정코드
	private String processName;	//공정이름
	private String prccessType;	//공겅구분
	private String description;	//설명
	private String comm;		//비고
}
