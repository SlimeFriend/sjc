package com.sjc.app.info.service;

import java.util.List;
import java.util.Map;


public interface InfoUserService {
	// 사용자 조회
	public List<InfoUserVO> userList(InfoUserVO userVO);
	// 사용자 수정
	public List<InfoUserVO> modifyUsers(List<InfoUserVO> userVOs);
	// 사용자 등록
    InfoUserVO insertUser(InfoUserVO userVO);
    // 사용자 삭제
    List<String> deleteUsers(List<String> userIds);
    // 사용자,권한 삭제 프로시저
    List<String> deleteUsersProcedure(List<String> userIds);
    // 사용자 복사 - 테스트용
    public List<String> copyUsers(List<String> userIds);
    // 사용자 복사 조회 - 테스트용
    public List<InfoCopyLogVO> copyLogList();
    // 사용자 복사 상세 조회 - 테스트용
    public List<InfoCopyDetailVO> copyDetailList();
    
    // 그리드 API용 사용자 조회, 페이징
    public Map<String, Object> getUserListResponse(InfoUserVO infoUserVO);
    // 그리드 API용 사용자 정보
    public List<InfoUserVO> getUserList(InfoUserVO infoUserVO);
    // 그리드 API용 사용자 수 조회
    public int getTotalUserCount(InfoUserVO infoUserVO);
    // 그리드 API용 VO -> Map
    public Map<String, Object> convertVoToMap(InfoUserVO infoUserVO);


}
