package com.mashreq.conference.domain.service.impl;


import com.mashreq.conference.infra.exceptions.ConferenceRoomErrorCode;
import com.mashreq.conference.infra.exceptions.ConferenceRoomException;
import com.mashreq.conference.persistence.entity.User;
import com.mashreq.conference.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository repository;

  public UserDetailsServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {

    User user = repository.findByEmail(email).orElseThrow(() ->
            new ConferenceRoomException(ConferenceRoomErrorCode.E_USER_DO_NOT_EXIST));
    com.mashreq.conference.domain.model.User userEntity = new com.mashreq.conference.domain.model.User(user.getUsername(), user.getEmail(), user.getPassword());
    return org.springframework.security.core.userdetails.User.builder()
            .username(userEntity.email())
            .password(userEntity.password())
            .build();
  }
}
