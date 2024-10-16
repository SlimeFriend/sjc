package com.sjc.app.mt.service;

import com.sjc.app.pr.service.PlanVO;
import java.util.List;

public interface ProductionPlanService {
    // 모든 생산 계획을 가져오는 메서드
    List<PlanVO> getAllProductionPlans();

    // 특정 생산 계획 코드에 따라 자재 목록을 가져오는 메서드
    List<MtVO> getMaterialsByPlanCode(String planCode);
}
