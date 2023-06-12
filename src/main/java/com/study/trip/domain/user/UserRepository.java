package com.study.trip.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);


	Optional<Users> findByProviderAndProviderId(String provider, String providerId);
}
