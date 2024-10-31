package com.sjc.app.mt.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.sjc.app.mt.service.MtInService;
import com.sjc.app.mt.service.MtInVO;

@Controller
public class MtInController {

    @Autowired
    private MtInService mtInService;

    /**
     * 품질검사 완료된 입고 목록 페이지로 이동
     * @param model 뷰로 데이터를 전달하기 위한 모델 객체
     * @return 입고 목록을 표시하는 mt/mtInList 페이지 경로
     */
    @GetMapping("/mtInList")
    public String getMtInList(Model model) {
        // 품질검사 완료된 입고 목록 가져오기
        List<MtInVO> mtInList = mtInService.getCompletedMtInList();
        model.addAttribute("mtInList", mtInList); // 모델에 입고 목록 추가

        return "mt/mtInList"; // 입고 목록만 표시
    }

}
