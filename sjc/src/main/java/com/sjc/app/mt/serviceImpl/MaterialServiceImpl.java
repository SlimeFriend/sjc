package com.sjc.app.mt.serviceImpl;

import java.util.List;

import com.sjc.app.mt.mapper.MaterialMapper;
import com.sjc.app.mt.service.MaterialService;
import com.sjc.app.mt.service.MtVO;

import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {
    
    private final MaterialMapper materialMapper;

    public MaterialServiceImpl(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }

    @Override
    public List<MtVO> getAllMaterials() {
        return materialMapper.selectAllMaterials(); // 모든 자재를 가져오는 메서드
    }

    @Override
    public MtVO getMaterialById(String mtCode) {
        return materialMapper.selectMaterialById(mtCode); // 자재 ID로 자재 정보를 가져오는 메서드
    }

    @Override
    public List<MtVO> getMaterialsByPlanCode(String planCode) {
        return materialMapper.selectMaterialsByPlanCode(planCode); // 생산 계획 코드로 자재 목록을 가져오는 메서드
    }
}
