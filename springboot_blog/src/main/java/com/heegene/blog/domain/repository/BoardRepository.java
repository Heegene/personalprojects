package com.heegene.blog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heegene.blog.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	// Repository interface로 정의하고 jparepo 상속받음
	// generic type에는 Entity클래스와 PK 타입을 명시
	
}
