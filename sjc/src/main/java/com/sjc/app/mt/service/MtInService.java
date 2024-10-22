package com.sjc.app.mt.service;

import java.util.List;

public interface MtInService {



    // 입고 목록 조회
    List<MtInVO> getMtInList();

    // 입고 등록
    void insertMtIn(MtInVO mtInVO);

    // 입고 삭제
    void deleteMtIn(String inCode);
}