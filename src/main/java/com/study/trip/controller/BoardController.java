package com.study.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.study.trip.service.BoardService;
import com.study.trip.domain.board.Board;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/board/save")
	public String save() {
		return "layout/board/board-save";
	}

	@GetMapping("/board/{id}/update")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("board", boardService.detail(id));
		return "layout/board/board-update";
	}

	/**
	 * 글상세 페이지
	 */
	@GetMapping("/board/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("board", boardService.detail(id));
		boardService.updateCount(id);
		return "layout/board/board-detail";
	}

	@GetMapping("/user/mypage/mypost/{id}")
	public String mydetail(@PathVariable Long id, Model model) {
		model.addAttribute("board", boardService.detail(id));
		boardService.updateCount(id);
		return "layout/board/board-detail";
	}


}
