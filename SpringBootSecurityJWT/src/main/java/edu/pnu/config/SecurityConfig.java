package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.config.filter.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AuthenticationConfiguration authenConfig;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(ah->ah
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasAnyRole("ADMIN")
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().permitAll());
		
		http.formLogin(fl->fl.disable());
		http.httpBasic(basic ->basic.disable());
		
		// URL 호출까지만 시큐리티 세션을 유지하고 이후에는 세션 유지 안함 (톰캣 세션은 유지)
		// 이후 토큰으로 payload에 있는 username 객체를 이용하여 DB 접근
		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
		
		http.csrf(cf->cf.disable());	
		http.headers(h->h.frameOptions(f->f.disable()));
		
		// 개발자가 작성한 authentication 필터 추가
		http.addFilter(new JWTAuthenticationFilter(authenConfig.getAuthenticationManager()));
		
		return http.build();
		
	}

}
