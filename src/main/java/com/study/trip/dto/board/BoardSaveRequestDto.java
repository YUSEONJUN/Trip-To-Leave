package com.study.trip.dto.board;

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
public class BoardSaveRequestDto {

	private String title;
	private String content;
	private int count;
	private User user;
	private String startday;
	private String lastday;
	private int pnum;

	private String city;

	private String state;

	public Board toEntity() {
		return Board.builder()
			.title(title)
			.content(content)
			.count(0)
			.user(user)
			.startday(startday)
			.lastday(lastday)
			.pnum(pnum)
			.city(city)
			.state(state)
			.build();
	}

	public void setUser(User user) {
		this.user = user;
	}
}