package com.sjc.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //BCrypt 단방향 해시 알고리즘
	}

	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 적용될 Security 설정
		// => URI에 적용될 권한
		http.authorizeHttpRequests(authorize -> authorize
//				.requestMatchers("/", "all", "logins").permitAll()
				//로그인 페이지
				.requestMatchers("logins").permitAll()
//				.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
	            // Spring Boot Admin과 Actuator 엔드포인트 허용
	            .requestMatchers("/actuator/**", "/instances/**", "/assets/**", "/login", "/error").permitAll()
				.anyRequest().authenticated() // 위 경로 제외 인증되면 접속 허가. 권한 필요 없음.
		)
		.formLogin(formlogin -> formlogin
				//로그인페이지
			    .loginPage("/logins")
			    .loginProcessingUrl("/login")//스프링 시큐리티
				.defaultSuccessUrl("/main")
                // 로그인 실패 처리
                .failureUrl("/logins?error=true") // 실패시 이동할 URL
                .failureHandler((request, response, exception) -> {
                    String errorMessage = "아이디 또는 비밀번호가 올바르지 않습니다.";
                    request.getSession().setAttribute("errorMessage", errorMessage);
                    response.sendRedirect("/logins?error=true");
                })
				)
		.logout(logout -> logout
				.logoutSuccessUrl("/main")
				.invalidateHttpSession(true)); // 로그아웃 시 세션 삭제
		
		http.csrf(csrf -> csrf.disable()); // 개발용.th에서도 삭제
				
		return http.build();
	}
}
