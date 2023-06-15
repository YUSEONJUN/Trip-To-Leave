package com.study.trip.domain.user;




import com.study.trip.domain.BaseTimeEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //sequence, auto_increment

	@Column(nullable = false, length = 50, unique = true)
	private String username; //아이디

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 30)
	private String email;

	@Column(nullable = false, length = 20)
	private String nickname; //닉네임

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@Column
	private String provider;

	@Column
	private String providerId;

	/**
	 * 비밀번호 암호화 메소드
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 권한 메소드
	 */
	public String getRoleKey() {
		return this.role.getKey();
	}

	/**
	 * 회원수정 메소드
	 */
	public void update(String password, String nickname) {
		this.password = password;
		this.nickname = nickname;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
