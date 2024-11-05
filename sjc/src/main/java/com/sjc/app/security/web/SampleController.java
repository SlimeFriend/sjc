package com.sjc.app.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class SampleController {
	@GetMapping("/")
	public String root() {
		return "redirect:main";
	}
	
//	@GetMapping("main")
//	public void main() {}

	@GetMapping("main")
	public String main() {
		return "mainChart";
	}
	
	@GetMapping("error")
	public void error() {}
	
	@GetMapping("all")
	public void all() {}
	
	@GetMapping("user")
	public void user() {}
	
	@GetMapping("admin")
	public void admin() {}
	
//    @GetMapping("/logins")
//    public void logins() {}
    
    @GetMapping("/logins")
    public String logins(@RequestParam(value = "error", required = false) String error,
                      HttpSession session,
                      Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return "logins";
    }    
    
    
}
