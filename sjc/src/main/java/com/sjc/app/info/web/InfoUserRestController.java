package com.sjc.app.info.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoUserService;
import com.sjc.app.security.service.UserVO;

import lombok.RequiredArgsConstructor;

@RestController // @Controller + 모든 메소드에 @ResponseBody를 적용 : AJAX 사용 O, Model 사용 X
				// @ResponseBody : AJAX
@RequiredArgsConstructor
public class InfoUserRestController {
	
	private final InfoUserService userService;

	@GetMapping("/restTest")
	public String restTest() {
		return "test/main";
	}
	
//	//전체조회 : GET + URI(자원 => users)
//	//REST => 사실상 Model 객체 사용하지 않음
//	@GetMapping("users")
//	public List<UserVO> userList(){
//		return userService.userList();
//	}
	
	@GetMapping("users")
	public List<UserVO> userList(UserVO uservo){
		return userService.userList(uservo);
	}
	
//	//단건조회 : GET + URI(자원 => users)
//	@GetMapping("users/{userId}") //PathVariable방식
//	public UserVO userInfo(@PathVariable Integer userId) {
//		UserVO userVO = new UserVO();
//		userVO.setUserId(userId);
//		
//		return userService.userInfo(userVO);
//	}
//	//등록	: POST + URI(자원 => users)
//	@PostMapping("users")
//	public int userInsert(@RequestBody UserVO userVO) {
//		return userService.userInsert(userVO);
//	}
//	//수정	: PUT + URI(자원 => users)
//	@PutMapping("users/{userId}")
//	public Map<String, Object> userUpdate(@PathVariable Integer userId, @RequestBody UserVO userVO) {
//		userVO.setUserId(userId);
//		return userService.userUpdate(userVO);
//	}
//	//삭제	: DELETE + URI(자원 => users)
//	@DeleteMapping("users/{userId}")
//	public Map<String, Object> userDelete(@PathVariable Integer userId){
//		return userService.userDelete(userId);
//	}
	
}
