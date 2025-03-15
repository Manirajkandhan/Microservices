package com.abc.mobilestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.mobilestore.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

	Optional<UserEntity> findByUsernameOrEmail(String username,String email);
}
