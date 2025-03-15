package com.abc.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.micro.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile,Integer> {

}
