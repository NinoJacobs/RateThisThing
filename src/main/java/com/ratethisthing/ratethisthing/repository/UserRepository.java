package com.ratethisthing.ratethisthing.repository;

import com.ratethisthing.ratethisthing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


}