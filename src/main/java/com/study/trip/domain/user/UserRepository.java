package com.study.trip.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);


	Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
