package com.sjc.app.info.service;

import java.util.List;

public interface InfoBomService {
    public List<String> registerBoms(List<String> mtCodes);
    public List<BomVO> bomList();
    public List<BomVO> bomDetailList();	
	
}
