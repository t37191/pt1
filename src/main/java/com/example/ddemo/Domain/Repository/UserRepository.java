package com.example.ddemo.Domain.Repository;

import com.example.ddemo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsername(String username);

    @Transactional
    @Modifying
    public void deleteByUsername(String username);
}
