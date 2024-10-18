	package com.sjc.app.info.web;
	
	import java.util.List;

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
	
		@GetMapping("users")
		public List<InfoUserVO> userList(InfoUserVO userVO){//@RequestBody 적용예정.토스트 ui
			return infoUserService.userList(userVO);
		}
	
	    @PutMapping("users")
	    public List<InfoUserVO> updateUsers(@RequestBody List<InfoUserVO> InfoUserVOs) {
	        return infoUserService.modifyUsers(InfoUserVOs);
	    }	
	
	    @PostMapping("users")
	    public InfoUserVO insertUser(@RequestBody InfoUserVO userVO) {
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
   
		@GetMapping("copyLogs")
		public List<InfoCopyLogVO> copyLogList(){//@RequestBody 적용예정.토스트 ui
			return infoUserService.copyLogList();
		}
		
		@GetMapping("copyDetails")
		public List<InfoCopyDetailVO> copyDetailList(){//@RequestBody 적용예정.토스트 ui
			return infoUserService.copyDetailList();
		}
	}
