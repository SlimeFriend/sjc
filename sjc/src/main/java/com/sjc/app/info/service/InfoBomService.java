package com.sjc.app.info.service;

import java.util.List;

public interface InfoBomService {
    public List<BomVO> registerBoms(List<BomVO> bomVOs);
    public List<BomVO> bomList();
    public List<BomVO> bomDetailList();	
	
}
