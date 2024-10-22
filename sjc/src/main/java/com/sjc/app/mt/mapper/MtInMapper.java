package com.sjc.app.mt.mapper;

import java.util.List;
import com.sjc.app.mt.service.MtInVO;

public interface MtInMapper {

    // 품질검사 완료된 자재 목록 조회
    List<MtInVO> getCompletedMaterials(); // 품질검사 완료된 자재만 조회

}
