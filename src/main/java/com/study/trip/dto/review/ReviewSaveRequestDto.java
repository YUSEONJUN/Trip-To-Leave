package com.study.trip.dto.review;

import com.study.trip.domain.review.Review;
import com.study.trip.domain.user.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveRequestDto {

	private String title;
	private String content;
	private int count;
	private Users user;


	public Review toEntity() {
		return Review.builder()
			.title(title)
			.content(content)
			.count(0)
			.user(user)
			.build();
	}

	public void setUser(Users user) {
		this.user = user;
	}



}
