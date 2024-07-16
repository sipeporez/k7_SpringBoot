package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rubypaper.domain.Board;
import com.rubypaper.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;

	@GetMapping("/getBoardList")
	public String getBoardList(Model model, Board board) {
		List<Board> boardList = bs.getBoardList(board);
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		bs.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board",bs.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		bs.updateBoard(board);
		return "redirect:getBoardList";
//		return "forward:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		bs.deleteBoard(board);
		return "forward:getBoardList";
	}
}
