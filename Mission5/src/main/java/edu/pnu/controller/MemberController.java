package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor 	// Lombok을 이용한 의존성 주입 어노테이션 (private final MemberService ms) 
public class MemberController {
	
	private final MemberService ms;

	@PostConstruct
	private void init() {
		System.out.println("MemberController 호출");
	}
	
	@GetMapping("/members")
	public List<MemberDTO> getAllMem() {
		ms.setMethod("GET");				//setter로 호출한 method를 저장
		return ms.getMember();
	}

	@GetMapping("/member")
	public List<MemberDTO> getMemById(int id) {
		ms.setMethod("GET");
		return ms.getMemberById(id);
	}

	@PostMapping("/member")
	public List<MemberDTO> addMemPost(String name, String pass) {
		ms.setMethod("POST");
		return ms.addMemberByPost(name, pass);
	}

	@PutMapping("/member")
	public List<MemberDTO> updateMemById(int id, String name, String pass) {
		ms.setMethod("PUT");
		return ms.updateMemberById(id, name, pass);
	}

	@DeleteMapping("/member")
	public int deleteMemById(int id) {
		ms.setMethod("DELETE");
		return ms.deleteMemberById(id);
	}

	// @RequestBody 를 써야 Body에서 JSON 데이터를 가져올 수 있음
	@PostMapping("/memberJSON")
	public List<MemberDTO> addMemberJSON(@RequestBody List<MemberDTO> memberDTOList) {
		ms.setMethod("POST");
		return ms.addMemberJSON(memberDTOList);
	}
}
