package com.study.trip.domain.reviewReply;

import javax.persistence.*;


import com.study.trip.domain.BaseTimeEntity;
import com.study.trip.domain.board.Board;
import com.study.trip.domain.review.Review;
import com.study.trip.domain.user.User;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ReviewReply extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 500)
	private String content;

	@ManyToOne
	@JoinColumn(name = "reviewId")
	private Review review;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;


	public void save(Review review, User user) {
		this.review = review;
		this.user = user;
	}
}