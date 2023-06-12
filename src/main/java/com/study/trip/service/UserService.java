package com.study.trip.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.trip.config.auth.PrincipalDetail;
import com.study.trip.domain.user.Users;
import com.study.trip.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 회원가입 로직
	 */
	@Transactional
	public Long save(Users user) {
		String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPw);
		return userRepository.save(user).getId();
	}

	/**
	 * 회원수정 로직
	 */
	@Transactional
	public Long update(Users user, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		Users userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + user.getId()));
		userEntity.update(bCryptPasswordEncoder.encode(user.getPassword()), user.getNickname());
		principalDetail.setUser(userEntity); //시큐리티 세션 정보 변경
		return userEntity.getId();
	}

	@Transactional(readOnly = true)
	public Users findUser(String username){
		Users user = userRepository.findByUsername(username).orElseGet(()->{
			return new Users();
		});
		return user;
	}

}
