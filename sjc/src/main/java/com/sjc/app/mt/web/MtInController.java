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

    // 입고 자재 및 발주 자재 목록 페이지로 이동 (한 페이지에서 처리)
    @GetMapping("/mtInList")
    public String getMtInListAndCompletedMaterials(Model model) {
        // 입고 목록 가져오기
        List<MtInVO> mtInList = mtInService.getMtInList();
        model.addAttribute("mtInList", mtInList);

        // 검사 완료된 발주 자재 목록 가져오기
        List<MtInVO> completedMaterials = mtInService.getCompletedMaterials();
        model.addAttribute("completedMaterials", completedMaterials);

        return "mt/mtInList"; // 한 페이지에서 입고 목록과 발주 자재 목록을 함께 표시
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
