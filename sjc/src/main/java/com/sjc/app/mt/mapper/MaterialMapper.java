package com.sjc.app.mt.mapper;

import com.sjc.app.mt.service.MtVO;
import java.util.List;

public interface MaterialMapper {

    // 모든 자재 목록 조회
    List<MtVO> selectAllMaterials();

    // 특정 자재 조회 (자재 코드로 조회)
    MtVO selectMaterialById(String mtCode);

    // 자재 코드 목록으로 자재 조회 (복수의 자재 코드에 따른 조회)
    List<MtVO> selectMaterialsByCodes(List<String> materialCodes);

    // 생산 계획 코드에 따른 자재 조회 (특정 생산 계획에 필요한 자재 조회)
    List<MtVO> selectMaterialsByPlanCode(String planCode);
}
