package com.sjc.app.mt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.StockMapper;
import com.sjc.app.mt.service.MtInVO;
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

    // 자재 구분 업데이트 기능
    @Override
    public void updateMaterialType(String mtCode, String materialType) {
        stockMapper.updateMaterialType(mtCode, materialType); // 자재 구분 업데이트
    }

    // 입고 품질검사가 완료된 자재 목록 가져오기
    @Override
    public List<MtVO> getCompletedInspectionMaterials() {
        return stockMapper.getCompletedInspectionMaterials(); // 입고 품질검사 완료 자재 목록 조회
    }

    // 현재 재고 업데이트 기능
    @Override
    public void updateCurrentStock(String mtCode, Integer quantity) {
        stockMapper.updateCurrentStock(mtCode, quantity); // 현재 재고 업데이트
    }

    // 로트번호별 자재 수량을 가져오는 기능
    @Override
    public List<MtInVO> getMaterialsByLotNo(String mtCode) {
        return stockMapper.getMaterialsByLotNo(mtCode); // 로트번호별 자재 수량 조회
    }

    // 로트번호별 자재 수량의 합계를 가져오는 기능
    @Override
    public Integer getTotalQuantityByLotNo(String mtCode) {
        return stockMapper.getTotalQuantityByLotNo(mtCode); // 로트번호별 자재 수량 합계 조회
    }
}
