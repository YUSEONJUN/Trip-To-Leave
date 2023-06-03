package com.study.trip.controller;

import com.study.trip.config.auth.PrincipalDetail;
import com.study.trip.domain.accompany.Accompany;
import com.study.trip.domain.reply.Reply;
import com.study.trip.service.AccompanyService;
import com.study.trip.service.ReplyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.study.trip.service.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class AccompanyController {

    private final AccompanyService accompanyService;

    @PostMapping("/board/{boardId}/accompany")
    public void save(@PathVariable Long boardId,
                     @RequestBody Accompany accompany,
                     @AuthenticationPrincipal PrincipalDetail principalDetail) {
        accompanyService.accompanySave(boardId, accompany, principalDetail.getUser());
    }

}
