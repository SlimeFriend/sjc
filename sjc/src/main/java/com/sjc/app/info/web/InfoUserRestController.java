	package com.sjc.app.info.web;
	
	import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.app.info.service.InfoUserService;
import com.sjc.app.security.service.UserVO;

import lombok.RequiredArgsConstructor;
	
	@RestController
	@RequiredArgsConstructor
	public class InfoUserRestController {
		
		private final InfoUserService infoUserService;
	
		@GetMapping("users")
		public List<UserVO> userList(UserVO userVO){
			return infoUserService.userList(userVO);
		}
	
	    @PutMapping("users")
	    public List<UserVO> updateUsers(@RequestBody List<UserVO> UserVOs) {
	        return infoUserService.modifyUsers(UserVOs);
	    }	
	
	    @PostMapping("users")
	    public UserVO insertUser(@RequestBody UserVO userVO) {
	        return infoUserService.insertUser(userVO);
	    }

	    @DeleteMapping("users")
	    public List<String> deleteUsers(@RequestBody List<String> userIds) {
	        return infoUserService.deleteUsers(userIds);
	    }
	    
	    @PutMapping("copyUsers")
	    public List<String> copyUsers(@RequestBody List<String> userIds) {
	    	return infoUserService.copyUsers(userIds);
	    }
   
		
	}
