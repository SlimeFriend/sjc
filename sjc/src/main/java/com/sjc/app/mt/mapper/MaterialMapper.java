package com.sjc.app.mt.mapper;

import com.sjc.app.mt.service.MtVO;
import java.util.List;

public interface MaterialMapper {

    List<MtVO> selectAllMaterials();
    MtVO selectMaterialById(String mtCode);
    List<MtVO> selectMaterialsByPlanCode(String planCode);
    List<MtVO> selectMaterialsByCodes(List<String> materialCodes); // 선택된 자재들 가져오기

}