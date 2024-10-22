package com.sjc.app.mt.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjc.app.mt.service.MtInService;
import com.sjc.app.mt.service.MtInVO;

@Controller
public class MtInController {

    @Autowired
    private MtInService mtInService;

    // 품질검사 완료된 입고 목록 페이지로 이동
    @GetMapping("/mtInList")
    public String getMtInList(Model model) {
        // 품질검사 완료된 입고 목록 가져오기
        List<MtInVO> mtInList = mtInService.getCompletedMtInList();
        model.addAttribute("mtInList", mtInList);

        return "mt/mtInList"; // 입고 목록만 표시
    }


}
