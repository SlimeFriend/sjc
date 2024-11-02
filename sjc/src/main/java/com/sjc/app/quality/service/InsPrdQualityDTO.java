package com.sjc.app.quality.service;

import com.sjc.app.pr.service.PDetailVO;
import com.sjc.app.pr.service.POrderVO;
import com.sjc.app.pr.service.PlanDVO;
import com.sjc.app.security.service.UserVO;

import lombok.Data;

@Data
public class InsPrdQualityDTO {
	private PDetailVO pDetailVO;
	private POrderVO pOrderVO;
	private PlanDVO planDVO;
	private UserVO userVO;


}
