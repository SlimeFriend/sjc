package com.sjc.app.mt.service;

import java.util.List;

public interface StockService {
    // 전체 자재 목록을 가져오는 메서드
    List<MtVO> getAllMaterials();

    // 안전재고에서 현재 재고로 수량을 이동하는 메서드
    boolean moveSafetyStockToCurrent(String mtCode, Integer quantity);

    // 자재 구분을 업데이트하는 메서드
    void updateMaterialType(String mtCode, String materialType);
}
