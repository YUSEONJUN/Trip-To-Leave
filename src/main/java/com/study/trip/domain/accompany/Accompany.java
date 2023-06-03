package com.study.trip.domain.accompany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.trip.domain.BaseTimeEntity;
import com.study.trip.domain.board.Board;
import com.study.trip.domain.reply.Reply;
import com.study.trip.domain.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
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


    private boolean accepted;

    private boolean attended;

    public void accept() {
        this.accepted = true;
    }

    public void reject() {
        this.accepted = false;
    }
    // 생략
    public void attend() {
        this.attended = true;
    }

    public void absent() {
        this.attended = false;
    }

    public void save(Board board, User user) {
        this.board = board;
        this.user = user;
    }
}