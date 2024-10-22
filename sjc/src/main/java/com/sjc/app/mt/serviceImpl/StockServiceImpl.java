package com.sjc.app.mt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.StockMapper;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.StockService;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    // 전체 자재 목록 조회
    @Override
    public List<MtVO> getAllMaterials() {
        return stockMapper.getAllMaterials();
    }

    // 안전재고에서 현재 재고로 수량을 이동하는 기능
    @Override
    public boolean moveSafetyStockToCurrent(String mtCode, Integer quantity) {
        // 현재 안전재고와 현재 재고 확인
        MtVO material = stockMapper.getMaterialByCode(mtCode);
        
        if (material != null && material.getSafetyStock() != null && material.getSafetyStock() >= quantity) {
        	//자재조건이 DB에 있는지 확인 없을 시 false  + 안전재고값이 있는지 확인 없을 시 false + 안전재고보다 현재수량이 같거나 많으면 false 모두 충족시키기 
           
            stockMapper.updateSafetyStock(mtCode, -quantity); // 안전재고 감소
            //mtCode에 있는 안전재고를 옮기려는 수량만큼 - 처리 
            stockMapper.updateCurrentStock(mtCode, quantity); // 현재 재고 증가
            // mtCode에 있는 현재재고를 받으려는 수량만큼 + 처리
            return true;
        } else {
            return false; // 안전재고가 부족하거나 자재 정보가 없을 경우 실패
        }
    }

    // 자재 구분 업데이트 기능 추가
    @Override
    public void updateMaterialType(String mtCode, String materialType) {
        stockMapper.updateMaterialType(mtCode, materialType); // 자재 구분 업데이트
    }
}
