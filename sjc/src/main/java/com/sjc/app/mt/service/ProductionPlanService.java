package com.sjc.app.mt.service;

import com.sjc.app.pr.service.PlanVO;
import java.util.List;

public interface ProductionPlanService {
    List<PlanVO> getAllProductionPlans(); // 모든 생산 계획을 가져오는 메서드

    
}