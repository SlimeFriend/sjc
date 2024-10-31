package com.sjc.app.mt.mapper;

import com.sjc.app.mt.service.MtVO;
import com.sjc.app.pr.service.PlanVO;
import java.util.List;

public interface ProductionPlanMapper {

    // 모든 생산 계획을 조회하는 메서드
    List<PlanVO> selectAllProductionPlans();

    // 특정 생산 계획 코드에 해당하는 자재 목록을 조회하는 메서드
    List<MtVO> selectMaterialsByPlanCode(String planCode);
}
