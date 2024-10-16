package com.sjc.app.mt.mapper;

import com.sjc.app.mt.service.MtVO;
import java.util.List;

public interface MaterialMapper {
    // 모든 자재 조회
    List<MtVO> selectAllMaterials();

    // 특정 자재 조회
    MtVO selectMaterialById(String mtCode);

    // 자재 코드 목록에 따른 자재 조회
    List<MtVO> selectMaterialsByCodes(List<String> materialCodes);

    // 생산 계획 코드에 따른 자재 조회
    List<MtVO> selectMaterialsByPlanCode(String planCode);
}
