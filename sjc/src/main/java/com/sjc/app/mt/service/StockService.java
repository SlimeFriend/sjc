package com.sjc.app.mt.service;

import java.util.List;

public interface StockService {

    // 전체 자재 목록을 가져오는 메서드
    List<MtVO> getAllMaterials();

    // 입고 품질검사가 완료된 자재 목록을 가져오는 메서드
    List<MtVO> getCompletedInspectionMaterials();  

    // 자재 구분을 업데이트하는 메서드
    void updateMaterialType(String mtCode, String materialType);

    // 현재 재고를 업데이트하는 메서드
    void updateCurrentStock(String mtCode, Integer quantity);

    // 로트번호별 자재 수량을 가져오는 메서드
    List<MtInVO> getMaterialsByLotNo(String mtCode);

    // 로트번호별 자재 수량의 합계를 가져오는 메서드 (현재 재고에 반영)
    Integer getTotalQuantityByLotNo(String mtCode);
}
