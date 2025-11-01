package com.nkr4m.multithreading.optimisticlock.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkr4m.multithreading.optimisticlock.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}