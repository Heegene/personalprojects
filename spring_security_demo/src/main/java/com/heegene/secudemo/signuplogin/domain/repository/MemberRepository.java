package com.heegene.secudemo.signuplogin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heegene.secudemo.signuplogin.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	Optional<MemberEntity> findByEmail(String email);
	
	// email을 where 조건절로 해서 데이터를 가져올 수 있도록 findByEmail 메소드 정의 

}
