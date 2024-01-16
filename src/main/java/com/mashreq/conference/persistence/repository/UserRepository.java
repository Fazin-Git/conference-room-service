package com.mashreq.conference.persistence.repository;

import com.mashreq.conference.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  @Query("SELECT usr FROM User usr WHERE usr.email = :email")
  Optional<User> findByEmail(String email);

}