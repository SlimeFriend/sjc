package com.sjc.app.info.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjc.app.info.service.InfoUserService;
import com.sjc.app.security.service.UserVO;

@Controller // Route : 사용자의 요청(endpoint)와 그에 대한 처리
// URI + METHOD => Service => View
//@RequiredArgsConstructor
public class InfoUserController {
	// 해당 컨트롤러에서 제공하는 서비스
	private InfoUserService userService;
	
	@Autowired
	InfoUserController(InfoUserService userService){
		this.userService = userService;
	}
	
	// GET  : 조회, 빈페이지 // pk만 전달해서 삭제하는 경우는 가능.
	// POST : 데이터 조작(등록, 수정, 삭제)
	

//	// 전체조회
//	@GetMapping("userList")
//	public String userList(Model model) { // Model = Request + Response
//		
//		// 1) 기능 수행 => Service
//		List<UserVO> list = userService.userList();
//		// 2) 클라이언트에 전달할 데이터 담기
//		model.addAttribute("users", list);
//		return "user/list"; // 3) 데이터를 출력할 페이지 결정
//		// prefix + return + suffix => 실제 경로/ViewResolver
//        // classpath:/tuserlates/user/list.html		
//	}
//	
//	@GetMapping("userListGrid")
//	public String userListGrid(Model model) {
//		List<UserVO> list = userService.userList();
//		model.addAttribute("users", list);		
//		return "user/listGrid";
//	}
	
//	@GetMapping("ListGridFetch")
//	public String userGrid() {
//		return "user/listGridFetch";
//	}
	
	@GetMapping("infoUserListGridFetch")
	public String infoUserListGridFetch() {
		return "info/userListGridFetch";
	}
	
	@GetMapping("infoUserListGridUpdate")
	public String infoUserListGridUpdate() {
		return "info/userListGridUpdate";
	}
	
	@GetMapping("infoUserListGridApi")
	public String infoUserListGridApi() {
		return "info/userListGridApi";
	}
	
	@GetMapping("infoUserListGridApiInsert")
	public String infoUserListGridApiInsert() {
		return "info/userListGridApiInsert";
	}
	
	@GetMapping("infoUserListGrid")
	public String infoUserListGrid() {
		return "info/userListGrid";
	}
	
	
//	// 단건조회 : Get => QueryString(커맨드 객체 or @RequestParam), userloyeeId
//	@GetMapping("userInfo") // userInfo?userloyeeId=value
//	public String userInfo(UserVO userVO, Model model) {
//		UserVO findVO = userService.userInfo(userVO);
//		model.addAttribute("user",findVO);
//		// HttpServletRequest.setAttribute();
//	
//		return "user/info";
//        // classpath:/tuserlates/user/info.html => 실제 경로		
//		
//	}
//	
//	// 등록 - 페이지
//	@GetMapping("userInsert")
//	public String userInsertForm() {
//		return "user/insert";
//	}
//	
//	// 등록 - 처리 : Post => form태그를 통한 submit
//	//					=> QueryString(커맨드 객체)
//	@PostMapping("userInsert")
//	public String userInsertProcess(UserVO userVO) {
//		int eid = userService.userInsert(userVO);
//		String url = null;
//		if(eid > -1) {
//			// 정상적으로 등록된 경우
//			url = "redirect:userInfo?userloyeeId=" + eid;
//			// redirect: 가능한 경우 => GetMapping
//		}else {
//			// 등록되지 않은 경우
//			url = "redirect:userList";
//		}
//		
//		return url;
//	}
//	
//	// 수정 - 페이지 : Get, 조건이 필요 <=> 단건조회
//	@GetMapping("userUpdate") // userUpdate?userloyeeId=value
//	public String userUpdateForm(UserVO userVO, Model model) {
//		UserVO findVO = userService.userInfo(userVO);
//		model.addAttribute("user",findVO);
//		return "user/update";
//	}
//	
//	// 수정 - 처리 : Post, AJAX => QueryString
//	//@PostMapping("userUpdate")
//	@ResponseBody //AJAX // 응답, 리턴위에서 사용, 리턴에서 예외 발생(?)
//	public Map<String, Object> userUpdateAJAXQueryString(UserVO userVO){
//		return userService.userUpdate(userVO);
//	}
//	
//	// 수정 - 처리 : AJAX => JSON(@RequestBody)
//	@PostMapping("userUpdate")
//	@ResponseBody //AJAX
//	public Map<String, Object> userUpdateAJAXJSON(@RequestBody UserVO userVO){
//		return userService.userUpdate(userVO);
//	}
//	
//	// 삭제 - 처리 : Get => QueryString( @RequestParm )
//	@GetMapping("userDelete")
//	public String userDelete(Integer userloyeeId) {
//		userService.userDelete(userloyeeId);
//		return "redirect:userList";
//	}

}
