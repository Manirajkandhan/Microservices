package com.abc.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.jwtsecurity.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long>{

}
