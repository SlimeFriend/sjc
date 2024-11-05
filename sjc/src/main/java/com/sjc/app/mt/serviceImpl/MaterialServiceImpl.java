package com.sjc.app.mt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjc.app.mt.mapper.MaterialMapper;
import com.sjc.app.mt.service.MaterialService;
import com.sjc.app.mt.service.MtVO;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 모든 자재 목록을 조회하여 반환합니다.
     */
    @Override
    public List<MtVO> getAllMaterials() {
        return materialMapper.selectAllMaterials();
    }

    /**
     * 자재 코드(mtCode)를 통해 특정 자재를 조회하여 반환합니다.
     */
    @Override
    public MtVO getMaterialById(String mtCode) {
        return materialMapper.selectMaterialById(mtCode);
    }

    /**
     * 여러 자재 코드로 자재 목록을 조회하여 반환합니다.
     */
    @Override
    public List<MtVO> getMaterialsByCodes(List<String> materialCodes) {
        return materialMapper.selectMaterialsByCodes(materialCodes);
    }

    /**
     * 생산 계획 코드(planCode)에 따른 자재 목록을 조회하여 반환합니다.
     */
    @Override
    public List<MtVO> getMaterialsByPlanCode(String planCode) {
        return materialMapper.selectMaterialsByPlanCode(planCode);
    }
}
