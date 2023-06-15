package com.study.trip.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.study.trip.domain.board.Board;
import com.study.trip.domain.board.BoardRepository;
import com.study.trip.domain.reply.Reply;
import com.study.trip.domain.reply.ReplyRepository;
import com.study.trip.domain.user.User;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final BoardRepository boardRepository;

	@Transactional
	public void replySave(Long boardId, Reply reply, User user) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 boardId가 없습니다. id=" + boardId));
		reply.save(board, user);
		replyRepository.save(reply);
	}


	@Transactional
	public void replyDelete(Long replyId) {
		replyRepository.deleteById(replyId);
	}
}
