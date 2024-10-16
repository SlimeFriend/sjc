package com.sjc.app.mt.mapper;

import com.sjc.app.mt.service.MtVO;
import com.sjc.app.pr.service.PlanVO;
import java.util.List;

public interface ProductionPlanMapper {
    List<PlanVO> selectAllProductionPlans(); // 모든 생산 계획을 가져오는 메서드
    List<MtVO> selectMaterialsByPlanCode(String planCode); // 생산 계획에 해당하는 자재 조회
}
