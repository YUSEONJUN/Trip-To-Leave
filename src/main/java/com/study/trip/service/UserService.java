package com.study.trip.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.trip.config.auth.PrincipalDetail;
import com.study.trip.domain.user.User;
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
	public Long save(User user) {
		String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPw);
		return userRepository.save(user).getId();
	}

	/**
	 * 회원수정 로직
	 */
	@Transactional
	public Long update(User user, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + user.getId()));
		userEntity.update(bCryptPasswordEncoder.encode(user.getPassword()), user.getNickname());
		principalDetail.setUser(userEntity); //시큐리티 세션 정보 변경
		return userEntity.getId();
	}

	@Transactional(readOnly = true)
	public User findUser(String username){
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}

}
