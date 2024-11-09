package com.sjc.app.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjc.app.info.mapper.InfoBomMapper;
import com.sjc.app.info.mapper.InfoPrdMapper;
import com.sjc.app.info.service.BomVO;
import com.sjc.app.info.service.InfoBomService;
import com.sjc.app.info.service.PrdBomDTO;
import com.sjc.app.sales.service.ProductVO;
import com.sjc.app.security.service.LoginUserVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("info.bom")
@Slf4j
@Service // AOP 적용가능한 Bean
public class InfoBomServiceImpl implements InfoBomService {
	//private MeterRegistry registry;
	private InfoBomMapper infoBomMapper;
	private InfoPrdMapper infoPrdMapper;
	
	@Autowired
	InfoBomServiceImpl(InfoBomMapper infoBomMapper, InfoPrdMapper infoPrdMapper, MeterRegistry registry){
		this.infoBomMapper = infoBomMapper;
		this.infoPrdMapper = infoPrdMapper;
	}
	
	// BOM 등록
	@Override
    @Transactional
//	public List<String> registerBoms(List<String> mtCodes) {
	public List<BomVO> registerBoms(List<BomVO> bomVOs) {

		/*
        List<BomVO> BomVOs = new ArrayList<>();
        
        for(String mtCode : mtCodes) {
        	BomVO bomVO = new BomVO();
        	bomVO.setMtCode(mtCode);
        	BomVOs.add(bomVO);
        }
        
		infoBomMapper.insertBom();
				
        for(String mtCode : mtCodes) {
            infoBomMapper.insertBomDetail(mtCode);  // 단건 처리로 변경
        }

		return mtCodes;
		*/
		
		
		//infoBomMapper.insertBom(); // comm 추가 이전 버전.
		if (!bomVOs.isEmpty()) { 
		    infoBomMapper.insertBom(bomVOs.get(0)); 
		}
		
        for (BomVO bomVO : bomVOs) {
        	infoBomMapper.insertBomDetail(bomVO);  // 단건 처리로 변경
        }

        return bomVOs;		
		
		
	}
	
	// 제품, BOM 등록
	@Override
	@Transactional
	public List<BomVO> registerPrdBoms(List<BomVO> bomVOs) {
		
		// BOM 체크박스 선택
		if(bomVOs.get(0).getCustomSwitchBom().equals("true")) {
			infoBomMapper.insertBom(bomVOs.get(0));
			for (BomVO bomVO : bomVOs) {
				infoBomMapper.insertBomDetail(bomVO);  // 단건 처리로 변경
			}
		}
		
		infoPrdMapper.insertProduct(bomVOs.get(0));
		
        return bomVOs;	
	}
	
	// BOM 조회
	@Override
	public List<BomVO> bomList() {
		return infoBomMapper.selectBomAllList();
	}
	
	// BOM 상세 조회
	@Override
	public List<BomVO> bomDetailList(BomVO bomVO) {
		return infoBomMapper.selectBomDetailAllList(bomVO);
	}
	
	// 제품 수정
	@Override
	public int modifyPrd(ProductVO productVO) {
		return infoBomMapper.updatePrd(productVO);
	}
	
	// BOM 수정
	@Override
	public int modifyBom(BomVO bomVO) {
		return infoBomMapper.updateBom(bomVO);
	}
	
    // 제품, BOM 수정
	@Override
	@Transactional
	public Map<String, Object> modifyPrdBom(PrdBomDTO prdBomDTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String id = null;
        String name = null;
        if (authentication.getPrincipal() instanceof LoginUserVO) {
            LoginUserVO loginUserVO = (LoginUserVO) authentication.getPrincipal();
            //id = loginUserVO.getUserVO().getUserId();
            name = loginUserVO.getUserVO().getUserName();
        }
		
		
		ProductVO productVO = new ProductVO();
		productVO.setPrdCode(prdBomDTO.getPrd().getPrdCode());
		productVO.setBomCode(prdBomDTO.getBom().getBomCode());
		
		infoBomMapper.updatePrdNull(productVO);
		infoBomMapper.updatePrd(productVO);
		
		BomVO bomVO = new BomVO();
		bomVO.setBomCode(prdBomDTO.getBom().getBomCode());
		bomVO.setPrdCode(prdBomDTO.getPrd().getPrdCode());
		bomVO.setManager(name);
		
		infoBomMapper.updateBomNull(productVO);
		infoBomMapper.updateBom(bomVO);
		
		
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("message", "modifyPrdBom 완료.");
	    
	    return response;
	}

	@Override
	@Transactional
	public List<BomVO> deleteBoms(List<BomVO> bomVOs) {
		for(BomVO bomVO : bomVOs) {
			infoBomMapper.deleteBom(bomVO);
			infoBomMapper.deleteBomDetail(bomVO);
		}
		return bomVOs;
	}
}
