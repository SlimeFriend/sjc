package com.sjc.app.mt.service;

import com.sjc.app.mt.service.MtVO;
import java.util.List;

public interface MaterialService {
    List<MtVO> getAllMaterials(); // 모든 자재 가져오는 메서드
    MtVO getMaterialById(String mtCode); // 자재 ID로 자재를 가져오는 메서드
    List<MtVO> getMaterialsByPlanCode(String planCode); // 생산 계획 코드로 자재 목록을 가져오는 메서드
}
