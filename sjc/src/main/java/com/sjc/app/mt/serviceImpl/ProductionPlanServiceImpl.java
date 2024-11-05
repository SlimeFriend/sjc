package com.sjc.app.mt.serviceImpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sjc.app.mt.mapper.ProductionPlanMapper;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.ProductionPlanService;
import com.sjc.app.pr.service.PlanVO;

@Service
public class ProductionPlanServiceImpl implements ProductionPlanService {

    private final ProductionPlanMapper productionPlanMapper;

    /**
     * ProductionPlanMapper를 주입받는 생성자
     */
    public ProductionPlanServiceImpl(ProductionPlanMapper productionPlanMapper) {
        this.productionPlanMapper = productionPlanMapper;
    }

    /**
     * 모든 생산 계획을 페이징하여 조회합니다.
     */
    @Override
    public List<PlanVO> getAllProductionPlans() {
        int limit = 8;   // 페이지당 데이터 개수
        int offset = 0;   // 기본값 (첫 페이지)

        return productionPlanMapper.selectAllProductionPlans(limit, offset);
    }

    /**
     * 생산 계획 코드에 따른 자재 목록 조회
     */
    @Override
    public List<MtVO> getMaterialsByPlanCode(String planCode) {
        return productionPlanMapper.selectMaterialsByPlanCode(planCode);
    }
    
    /**
     * 페이징 적용된 생산 계획 목록 조회
     */
    @Override
    public List<PlanVO> getAllProductionPlansWithPaging(int limit, int offset) {
        return productionPlanMapper.selectAllProductionPlans(limit, offset);
    }
}
