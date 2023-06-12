package com.study.trip.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.study.trip.domain.Accompany.Accompany;
import com.study.trip.domain.Accompany.AccompanyRepository;
import com.study.trip.domain.board.Board;
import com.study.trip.domain.board.BoardRepository;
import com.study.trip.domain.user.Users;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccompanyService {

	private final AccompanyRepository accompanyRepository;
	private final BoardRepository boardRepository;

	@Transactional
	public void accompanySave(Long boardId, Accompany accompany, Users user) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardId));
		accompany.save(board, user);
		accompanyRepository.save(accompany);
	}


   /* @Transactional
    public void replyDelete(Long replyId) {
        replyRepository.deleteById(replyId);
    }*/
}