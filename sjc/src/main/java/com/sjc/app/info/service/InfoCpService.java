package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.sales.service.CpVO;

public interface InfoCpService {
	// 업체 조회
	public List<CpVO> cpList(CpVO cpVO);
	// 업체 수정
	public List<CpVO> modifyCps(List<CpVO> CpVOs);
}
