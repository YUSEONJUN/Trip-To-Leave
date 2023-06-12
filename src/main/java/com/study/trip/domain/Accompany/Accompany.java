package com.study.trip.domain.Accompany;

import javax.persistence.*;


import com.study.trip.domain.BaseTimeEntity;
import com.study.trip.domain.board.Board;
import com.study.trip.domain.user.User;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accompany extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "boardId")
	private Board board;

	public void save(Board board, User user) {
		this.board = board;
		this.user = user;
	}
}