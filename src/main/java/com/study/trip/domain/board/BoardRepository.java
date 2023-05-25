package com.study.trip.domain.board;


import com.study.trip.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {

	@Modifying
	@Query("update Board p set p.count = p.count + 1 where p.id = :id")
	int updateCount(Long id);

	Page<Board> findByTitleContainingOrContentContaining(@Param("title") String title, @Param("content") String content, @Param("pageable") Pageable pageable);


	Page<Board> findByUser_Id(
			@Param(value = "userId")Long userId, Pageable pageable);
}