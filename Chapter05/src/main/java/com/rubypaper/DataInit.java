package com.rubypaper;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner{

	private final BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 1; i <= 5 ; i++) {
			boardRepo.save(Board.builder()
					.title("title1"+i)
					.writer("writer1"+i)
					.content("content1"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build());
		}
		
		for (int i = 1; i <= 5 ; i++) {
			boardRepo.save(Board.builder()
					.title("title2"+i)
					.writer("writer2"+i)
					.content("content2"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build());
		}
	}
}