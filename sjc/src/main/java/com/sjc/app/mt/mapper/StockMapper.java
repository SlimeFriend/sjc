package com.sjc.app.mt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sjc.app.mt.service.MtVO;

@Mapper
public interface StockMapper {
    
    // 전체 자재 목록 조회
    List<MtVO> getAllMaterials();

    // 자재 코드로 특정 자재 조회
    MtVO getMaterialByCode(@Param("mtCode") String mtCode);

    // 안전재고 업데이트 (수량 증가/감소)
    void updateSafetyStock(@Param("mtCode") String mtCode, @Param("quantity") Integer quantity);

    // 현재 재고 업데이트 (수량 증가/감소)
    void updateCurrentStock(@Param("mtCode") String mtCode, @Param("quantity") Integer quantity);
    
    // 자재 구분 업데이트
    void updateMaterialType(@Param("mtCode") String mtCode, @Param("materialType") String materialType);
}
