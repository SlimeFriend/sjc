package com.sjc.app.info.service;

import java.util.List;

import com.sjc.app.security.service.UserVO;

// 실제로 사용자에게 제공하는 기능

public interface UserService {
	// 전체 사용자정보 조회
	public List<UserVO> userList();
//	// 사용자정보 단건 조회
//	public UserVO userInfo(UserVO userVO);
//	// 사용자정보 단건 등록
//	public int userInsert(UserVO userVO);
//	// 사용자정보 단적 수정
//	public Map<String, Object> userUpdate(UserVO userVO);
//	// 사용자정보 단건 삭제
//	public Map<String, Object> userDelete(int userId);
}
