package com.heegene.secudemo.signuplogin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heegene.secudemo.signuplogin.domain.Role;
import com.heegene.secudemo.signuplogin.domain.entity.MemberEntity;
import com.heegene.secudemo.signuplogin.domain.repository.MemberRepository;
import com.heegene.secudemo.signuplogin.dto.MemberDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
	private MemberRepository memberRepository;
	
	@Transactional
	public Long joinUser(MemberDto memberDto) { // 회원가입 처리하는 메서드로, 비밀번호 암호화하여 저장 
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		
		return memberRepository.save(memberDto.toEntity()).getId();
	}
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
		
		MemberEntity userEntity = userEntityWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
			// 롤을 부여하는 코드(admin@example.com) 이면 admin 롤 부여
			// 회원가입시 롤을 부여할 수 있도록 role entity를 만드렁 매핑해 주는 것이 좋음
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		} 
		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
		// Springsecurity에서 제공하는 userdetails를 구현한 user를 반환함 (매개변수는 순서대로 아이디, 비밀번호, 권한리스트)
	}
	
	// 상세 정보를 조회하는 메서드로, 사용자의 계정 정보와  권한을 갖는 userdetails 인터페이스를 반환해야 함
	// 매개변수는 로그인 시 입력한 아이디, 엔티티의 PK가 아니라 유저를 식별할 수 있는 어떤 값을 의미함
	// spring security에서는 이걸 username이라는 이름으로 사용함
	// -> 여기서는 아이디를 이메일로, 로그인 하는 form에서 name="username" 이라고 지정했음
	

}
