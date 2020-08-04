package com.appress.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appress.spring.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long>{
	// JPA Repository 인터페이스를 확장해서 JPA 기술을 구현
	// 스프링 데이터 레포지토리 엔진이 자동으로 인식하여 CRUD 및 custom method 구현 가능
}
