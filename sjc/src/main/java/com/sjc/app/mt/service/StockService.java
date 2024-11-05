package com.sjc.app.mt.service;

import java.util.List;

public interface StockService {

    // 전체 자재 목록을 조회
    List<MtVO> getAllMaterials();

    // 입고 품질검사가 완료된 자재 목록 조회
    List<MtVO> getCompletedInspectionMaterials();

    // 자재 구분 업데이트
    void updateMaterialType(String mtCode, String materialType);

//    // 현재 재고 업데이트 (재고 추가 또는 차감)
//    void updateCurrentStock(String mtCode, Integer quantity);

    // 로트번호별 자재 수량 조회
    List<MtInVO> getMaterialsByLotNo(String mtCode);

    // 로트번호별 자재 수량 합계 조회
    Integer getTotalQuantityByLotNo(String mtCode);

    // 자재 코드 기반 자재 정보 조회
    MtVO selectMaterialByCode(String mtCode);

    // 로트 번호에 수량 추가 후 현재 재고 업데이트
    void addQuantityToLotAndUpdateStock(String mtCode, String lotNo, int quantity);

    // 자재 코드로 로트번호 목록 조회
    List<String> getLotNumbersByMtCode(String mtCode);
}
