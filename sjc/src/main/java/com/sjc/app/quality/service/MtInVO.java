package com.sjc.app.quality.service;

import java.util.Date;

import lombok.Data;


@Data
public class MtInVO {
	private String inCode;
	private String lotNo;
	private Date inDate;
	private int inquantity;
	private Date ed;
	private int userID;
	private String mtCode;
	private String cpCode;
	private String mtlOdCode;
	private String insCode;
	private String comm;
}
