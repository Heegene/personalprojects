package com.appress.spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appress.spring.domain.Journal;
import com.appress.spring.repository.JournalRepository;

@Controller
public class JournalController {
	
	@Autowired
	JournalRepository repo;
	
	@RequestMapping("/")
	public String index(Model model) {
		// repo.findAll()은 JPA repository를 상속한 인터페이스라 상속받은 메소드를 가지고 있음
		model.addAttribute("journal", repo.findAll());
		return "index";
		// "/" 경로로 오면 repo의 모든 정보를 담고 index.html로 이동
	}
	
	@RequestMapping(value = "/journal", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<Journal> getJournal() {
		return repo.findAll();
		// "/journal" 경로로 진입 시 json형식으로 repo의 모든 정보를 보냄 
	}
}
