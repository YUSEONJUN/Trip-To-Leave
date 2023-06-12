package com.study.trip.controller.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.trip.config.auth.PrincipalDetail;
import com.study.trip.domain.Accompany.Accompany;
import com.study.trip.service.AccompanyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AccompanyApiController {

	private final AccompanyService accompanyService;

	@PostMapping("/board/{boardId}/accompany")
	public void save(
		@PathVariable Long boardId,
		@RequestBody Accompany accompany,
		@AuthenticationPrincipal PrincipalDetail principalDetail
	) {
		accompanyService.accompanySave(boardId, accompany, principalDetail.getUser());
	}

}