package com.nexign.bootcamp.tokenservice.dao;

import com.nexign.bootcamp.tokenservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
