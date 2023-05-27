package com.study.trip.config.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.study.trip.config.auth.PrincipalDetail;
import com.study.trip.config.oauth.provider.GoogleUserInfo;

import com.study.trip.config.oauth.provider.KakaoUserInfo;
import com.study.trip.config.oauth.provider.NaverUserInfo;
import com.study.trip.config.oauth.provider.OAuth2UserInfo;
import com.study.trip.domain.user.KakaoProfile;
import com.study.trip.domain.user.Role;
import com.study.trip.domain.user.User;
import com.study.trip.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		return processOAuth2User(userRequest, oAuth2User);
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = null;
		KakaoProfile kakaoProfile = new KakaoProfile();

		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		}
		else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("username", "kakao" + "_" + kakaoProfile.getId());
			attributes.put("email", kakaoProfile.getKakao_account().getEmail());
			attributes.put("provider", "kakao");
			attributes.put("providerId", kakaoProfile.getId().toString());

			oAuth2UserInfo = new KakaoUserInfo(attributes);
		}

		Optional<User> userOptional = userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(),
			oAuth2UserInfo.getProviderId());

		User user;

		if (userOptional.isPresent()) {
			user = userOptional.get();
			user.setEmail(oAuth2UserInfo.getEmail());
			userRepository.save(user);
		} else {
			user = User.builder()
				.username(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
				.password(UUID.randomUUID().toString())
				.email(oAuth2UserInfo.getEmail())
				.nickname("소셜로그인")
				.role(Role.USER)
				.provider(oAuth2UserInfo.getProvider())
				.providerId(oAuth2UserInfo.getProviderId())
				.build();
			userRepository.save(user);
		}
		return new PrincipalDetail(user, oAuth2User.getAttributes());
	}
}