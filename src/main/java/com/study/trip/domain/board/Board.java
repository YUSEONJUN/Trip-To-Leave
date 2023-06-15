package com.study.trip.domain.board;

import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.trip.domain.BaseTimeEntity;
import com.study.trip.domain.reply.Reply;
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
public class Board extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob
	private String content;

	@Column
	private int count; //조회수

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
	private String startday;
	private String lastday;
	private int pnum;

	@Column
	private String city;

	@Column
	private String state;

	public void update(String title, String content, String startday, String lastday, int punm, String city, String state) {
		this.title = title;
		this.content = content;
		this.startday = startday;
		this.lastday = lastday;
		this.pnum = punm;
		this.city=city;
		this.state= state;
	}

	@OrderBy("id desc")
	@JsonIgnoreProperties({"board"})
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Reply> replyList;
}