package com.study.trip.domain.review;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.trip.domain.BaseTimeEntity;
import com.study.trip.domain.reviewReply.ReviewReply;
import com.study.trip.domain.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Review extends BaseTimeEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private int count;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

	@OrderBy("id desc")
	@JsonIgnoreProperties({"review"})
	@OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<ReviewReply> reviewReplyList;


}
