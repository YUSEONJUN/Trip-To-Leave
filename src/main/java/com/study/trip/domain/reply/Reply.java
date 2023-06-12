package com.study.trip.domain.reply;

import jakarta.persistence.*;

import com.study.trip.domain.BaseTimeEntity;
import com.study.trip.domain.board.Board;
import com.study.trip.domain.user.Users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Reply extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 500)
	private String content;

	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;

	public void save(Board board, Users user) {
		this.board = board;
		this.user = user;
	}

}