package com.sjc.app.mt.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.sjc.app.mt.service.MtOutService;

@Controller
public class MtOutController {

    @Autowired
    private MtOutService mtOutService;

    /**
     * 출고 내역 조회
     * @param model 뷰에 데이터를 전달하기 위한 모델 객체
     * @return mt/mtOutList 페이지 경로
     */
    @GetMapping("/mtOutList")
    public String listOutgoing(Model model) {
        List<Map<String, Object>> outList = mtOutService.getOutgoingList(); // 출고 내역 가져오기
        model.addAttribute("outList", outList); // 모델에 출고 내역 추가
        return "mt/mtOutList"; // 출고 내역 페이지 반환
    }

}
