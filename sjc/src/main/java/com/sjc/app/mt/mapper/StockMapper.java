package com.sjc.app.mt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.MtInVO;

@Mapper
public interface StockMapper {

    // 전체 자재 목록 조회
    List<MtVO> getAllMaterials();

    // 자재 코드로 특정 자재 정보 조회
    MtVO getMaterialByCode(@Param("mtCode") String mtCode);

    // 입고 품질검사가 완료된 자재 목록 조회
    List<MtVO> getCompletedInspectionMaterials();

//    // 현재 재고 업데이트 (수량 증가/감소)
//    void updateCurrentStock(@Param("mtCode") String mtCode, @Param("quantity") Integer quantity);

    // 자재 구분 업데이트
    void updateMaterialType(@Param("mtCode") String mtCode, @Param("materialType") String materialType);

    // 로트번호별 자재 수량 조회
    List<MtInVO> getMaterialsByLotNo(@Param("mtCode") String mtCode);

    // 로트번호별 자재 수량 합계 조회
    Integer getTotalQuantityByLotNo(@Param("mtCode") String mtCode);

    // 자재 코드에 따른 로트번호 목록 조회
    List<String> findLotNumbersByMtCode(@Param("mtCode") String mtCode);

    // 특정 로트번호의 수량 업데이트
    void updateLotQuantity(@Param("mtCode") String mtCode, @Param("lotNo") String lotNo, @Param("quantity") Integer quantity);

    // 로트 번호의 수량 추가 및 현재 재고 업데이트
    void addQuantityToLotAndUpdateStock(@Param("mtCode") String mtCode, @Param("lotNo") String lotNo, @Param("quantity") Integer quantity);
}
