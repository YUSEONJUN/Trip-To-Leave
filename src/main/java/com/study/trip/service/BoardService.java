package com.study.trip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.trip.domain.board.Board;
import com.study.trip.domain.board.BoardRepository;
import com.study.trip.domain.user.User;
import com.study.trip.dto.board.BoardSaveRequestDto;
import com.study.trip.dto.board.BoardUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	/**
	 * 글작성 로직
	 */
	@Transactional
	public Long save(BoardSaveRequestDto boardSaveRequestDto, User user) {
		boardSaveRequestDto.setUser(user);
		return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
	}

	/**
	 * 글목록 로직
	 */
	@Transactional(readOnly = true)
	public Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
		return boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
	}

	/**
	 * 글상세 로직
	 */
	@Transactional(readOnly = true)
	public Board detail(Long id) {
		return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
	}

	/**
	 * 글삭제 로직
	 */
	@Transactional
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}

	/**
	 * 글수정 로직
	 */
	@Transactional
	public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
		Board board = boardRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
		board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent(), boardUpdateRequestDto.getStartday(), boardUpdateRequestDto.getLastday(), boardUpdateRequestDto.getPnum(),boardUpdateRequestDto.getCity(),boardUpdateRequestDto.getState());
		return id;
	}

	/**
	 * 글 조회수 로직
	 */
	@Transactional
	public int updateCount(Long id) {
		return boardRepository.updateCount(id);
	}

	@Transactional
	public Page<Board> findByUser_Id(Long userId, Pageable pageable) {
		return boardRepository.findByUser_Id(userId, pageable);
	}

}
