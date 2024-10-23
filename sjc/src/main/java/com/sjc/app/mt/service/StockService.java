package com.sjc.app.mt.service;

import java.util.List;

public interface StockService {

    // 전체 자재 목록을 가져오는 메서드
    List<MtVO> getAllMaterials();

    // 입고 품질검사가 완료된 자재 목록을 가져오는 메서드
    List<MtVO> getCompletedInspectionMaterials();

    // 자재 구분을 업데이트하는 메서드
    void updateMaterialType(String mtCode, String materialType);

    // 현재 재고를 업데이트하는 메서드 (재고 추가 또는 차감)
    void updateCurrentStock(String mtCode, Integer quantity);

    // 로트번호별 자재 수량을 가져오는 메서드
    List<MtInVO> getMaterialsByLotNo(String mtCode);

    // 로트번호별 자재 수량의 합계를 가져오는 메서드
    Integer getTotalQuantityByLotNo(String mtCode);

    // 특정 자재 코드를 기반으로 자재 정보를 가져오는 메서드
    MtVO selectMaterialByCode(String mtCode);

    // 재고 조정 메서드 (재고 추가 및 차감 처리)
    void adjustStock(String mtCode, String lotNo, int newStock);

    // 자재 코드에 따른 로트번호 목록을 가져오는 메서드 (로트번호 목록 조회)
    List<String> getLotNumbersByMtCode(String mtCode);

    // 로트 번호에 수량을 추가하고 현재 재고를 업데이트하는 메서드
    void addQuantityToLotAndUpdateStock(String mtCode, String lotNo, int quantity);

    // 추가: 로트 번호에 따른 자재 상세 정보 조회
    List<MtInVO> getLotDetailsByMtCode(String mtCode);
}
