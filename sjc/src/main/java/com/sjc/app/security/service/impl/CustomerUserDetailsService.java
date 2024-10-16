package com.sjc.app.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sjc.app.security.mapper.UserMapper;
import com.sjc.app.security.service.LoginUserVO;
import com.sjc.app.security.service.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final 사용.
public class CustomerUserDetailsService implements UserDetailsService{
	
	private final UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Mapper를 활용해서 DB에 접근
		UserVO userVO = userMapper.getUserInfo(username);
		
		if(userVO == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new LoginUserVO(userVO);
	}
	
}
