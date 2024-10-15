package com.sjc.app.mt.mapper;

import com.sjc.app.mt.service.MtVO;
import java.util.List;

public interface MaterialMapper {
    List<MtVO> selectAllMaterials(); // 모든 자재를 가져오는 메서드
    MtVO selectMaterialById(String mtCode); // 자재 ID로 자재를 가져오는 메서드

    // 생산 계획 코드로 자재 목록을 가져오는 메서드 추가
    List<MtVO> selectMaterialsByPlanCode(String planCode); 
}