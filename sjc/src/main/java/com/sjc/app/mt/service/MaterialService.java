package com.sjc.app.mt.service;


import java.util.List;

public interface MaterialService {
    List<MtVO> getAllMaterials(); // 모든 자재 가져오기
    MtVO getMaterialById(String mtCode); // 특정 자재 가져오기
    List<MtVO> getMaterialsByPlanCode(String planCode); // 생산 계획에 해당하는 자재 가져오기
    List<MtVO> getMaterialsByCodes(List<String> materialCodes); // 선택된 자재 목록 가져오기

}
