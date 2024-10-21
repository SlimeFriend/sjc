package com.sjc.app.mt.service;

import java.util.List;

public interface MtInService {

    // 검사 완료된 자재 목록 가져오기
    List<MtInVO> getCompletedMaterials();

    // 입고 목록 조회
    List<MtInVO> getMtInList();

    // 입고 등록
    void insertMtIn(MtInVO mtInVO);

    // 입고 삭제
    void deleteMtIn(String inCode);
}