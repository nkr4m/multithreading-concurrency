package com.nkr4m.multithreading.optimisticlock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.nkr4m.multithreading.optimisticlock.dto.UserDTO;
import com.nkr4m.multithreading.optimisticlock.entity.User;
import com.nkr4m.multithreading.optimisticlock.repo.UserRepository;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UserDTO updateUser(UserDTO user) {
        try {
        	
        	User en = new User();
        	en.setId(user.getId());
        	en.setEmail(user.getEmail());
        	en.setName(user.getName());
        	en.setVersion(user.getVersion());
            // This will throw OptimisticLockException if version doesn't match
            User updatedUser = userRepository.saveAndFlush(en);
            System.out.println("✅ User updated successfully with version: " + updatedUser.getVersion()); // THIS IS NOT BRINGINGF THE LATEST VERSION THAT GETS STORED IN DB
            user.setVersion(updatedUser.getVersion());
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("❌ OptimisticLockException caught!");
            System.out.println("   Exception: " + e.getClass().getSimpleName());
            System.out.println("   Message: " + e.getMessage());
        }
		return user;
    }

    @Transactional
    public User createUser(String name, String email) {
        User user = new User(name, email);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
