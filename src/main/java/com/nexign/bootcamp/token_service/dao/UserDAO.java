package com.nexign.bootcamp.token_service.dao;

import com.nexign.bootcamp.token_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
