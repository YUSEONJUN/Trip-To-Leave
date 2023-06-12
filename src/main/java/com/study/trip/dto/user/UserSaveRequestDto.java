package com.study.trip.dto.user;

import com.study.trip.domain.user.Role;
import com.study.trip.domain.user.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

	private String username;
	private String password;
	private String email;
	private String nickname;
	private Role role;

	public Users toEntity() {
		return Users.builder()
			.username(username)
			.password(password)
			.email(email)
			.nickname(nickname)
			.role(Role.USER)
			.build();
	}
}