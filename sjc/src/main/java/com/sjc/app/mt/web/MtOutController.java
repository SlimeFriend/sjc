package com.sjc.app.mt.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.sjc.app.mt.service.MtOutService;

@Controller
public class MtOutController {

    @Autowired
    private MtOutService mtOutService;

    // 출고 내역 조회
    @GetMapping("/mtOutList")
    public String listOutgoing(Model model) {
        List<Map<String, Object>> outList = mtOutService.getOutgoingList();
        model.addAttribute("outList", outList);
        return "mt/mtOut";
    }


}