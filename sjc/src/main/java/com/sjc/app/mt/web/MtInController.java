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

    // 입고 목록 페이지로 이동
    @GetMapping("/mtInList")
    public String getMtInList(Model model) {
        // 입고 목록 가져오기
        List<MtInVO> mtInList = mtInService.getMtInList();
        model.addAttribute("mtInList", mtInList);

        return "mt/mtInList"; // 입고 목록만 표시
    }

    // 입고 등록 처리
    @PostMapping("/mtIn")
    public String insertMtIn(MtInVO mtInVO) {
        mtInService.insertMtIn(mtInVO);
        return "redirect:/mtInList"; // 입고 등록 후 목록으로 이동
    }

    // 입고 삭제 처리
    @PostMapping("/mtIn/delete")
    public String deleteMtIn(@RequestParam("inCode") String inCode) {
        mtInService.deleteMtIn(inCode);
        return "redirect:/mtInList"; // 삭제 후 목록으로 이동
    }
}
