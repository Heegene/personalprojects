package com.heegene.secudemo.signuplogin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heegene.secudemo.signuplogin.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	Optional<MemberEntity> findByEmail(String email);

}
