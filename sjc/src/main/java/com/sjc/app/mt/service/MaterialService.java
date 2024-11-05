package com.sjc.app.mt.service;

import java.util.List;

public interface MaterialService {

    /**
     * 모든 자재 목록을 가져오는 메서드
     */
    List<MtVO> getAllMaterials(); 

    /**
     * 특정 자재 코드를 기반으로 자재 정보 조회
     * @param mtCode 자재 코드
     * @return 해당 자재의 정보
     */
    MtVO getMaterialById(String mtCode);

    /**
     * 선택된 자재 코드 목록에 해당하는 자재 목록 조회
     * @param materialCodes 자재 코드 목록
     * @return 해당 자재들의 정보 목록
     */
    List<MtVO> getMaterialsByCodes(List<String> materialCodes); 

    /**
     * 생산 계획 코드에 따른 자재 목록을 조회
     * @param planCode 생산 계획 코드
     * @return 해당 생산 계획에 필요한 자재 목록
     */
    List<MtVO> getMaterialsByPlanCode(String planCode); 
}
