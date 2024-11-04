	package com.sjc.app.info.web;
	
	import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoCopyDetailVO;
import com.sjc.app.info.service.InfoCopyLogVO;
import com.sjc.app.info.service.InfoUserService;
import com.sjc.app.info.service.InfoUserVO;

import lombok.RequiredArgsConstructor;
	
	@RestController
	@RequiredArgsConstructor
	public class InfoUserRestController {
		
		private final InfoUserService infoUserService;
		// 사용자 조회
		@GetMapping("users")
		public List<InfoUserVO> userList(InfoUserVO userVO){
			return infoUserService.userList(userVO);
		}
		// 사용자 조회 그리드 API
	    @GetMapping("usersApi")
	    public Map<String, Object> infoUserList(InfoUserVO infoUserVO) {
	        return infoUserService.getUserListResponse(infoUserVO);
	    }
	    // 사용자 수정
	    @PutMapping("users")
	    public List<InfoUserVO> updateUsers(@RequestBody List<InfoUserVO> InfoUserVOs) {
	        return infoUserService.modifyUsers(InfoUserVOs);
	    }	
	    // 사용자 등록
	    @PostMapping("users")
	    public InfoUserVO insertUser(@RequestBody InfoUserVO userVO) {
	        return infoUserService.insertUser(userVO);
	    }
	    // 사용자 삭제
	    @DeleteMapping("users")
	    public List<String> deleteUsers(@RequestBody List<String> userIds) {
	        return infoUserService.deleteUsers(userIds);
	    }
	    // 사용자,권한 삭제 프로시저
	    @DeleteMapping("usersProcedure")
	    public List<String> deleteUsersProcedure(@RequestBody List<String> userIds) {
	    	return infoUserService.deleteUsersProcedure(userIds);
	    }
	    // 사용자 복사 등록 - 테스트
	    @PutMapping("copyUsers")
	    public List<String> copyUsers(@RequestBody List<String> userIds) {
	    	return infoUserService.copyUsers(userIds);
	    }
	    // 사용자 복사 조회 - 테스트
		@GetMapping("copyLogs")
		public List<InfoCopyLogVO> copyLogList(){
			return infoUserService.copyLogList();
		}
		// 사용자 복사 상세 조회 - 테스트
		@GetMapping("copyDetails")
		public List<InfoCopyDetailVO> copyDetailList(){
			return infoUserService.copyDetailList();
		}
	}
