package com.sjc.app.mt.mapper;

import java.util.List;
import com.sjc.app.mt.service.MtInVO;

public interface MtInMapper {



    // 입고 목록 조회
    List<MtInVO> getMtInList();

    // 입고 등록
    void insertMtIn(MtInVO mtInVO);

    // 입고 삭제
    void deleteMtIn(String inCode);
}