package com.nexign.bootcamp.token_service.dao;

import com.nexign.bootcamp.token_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 19:15
 */
@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
