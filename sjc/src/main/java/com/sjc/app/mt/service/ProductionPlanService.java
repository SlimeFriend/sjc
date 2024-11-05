package com.sjc.app.mt.service;

import com.sjc.app.pr.service.PlanVO;
import java.util.List;

public interface ProductionPlanService {

    // 모든 생산 계획을 조회
    List<PlanVO> getAllProductionPlans();

    // 특정 생산 계획 코드에 따른 자재 목록 조회
    List<MtVO> getMaterialsByPlanCode(String planCode);

}