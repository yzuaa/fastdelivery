package com.laioffer.fastdelivery.service;

import com.laioffer.fastdelivery.exception.UserAlreadyExistException;
import com.laioffer.fastdelivery.exception.UserNotExistException;
import com.laioffer.fastdelivery.model.Authority;
import com.laioffer.fastdelivery.model.User;
import com.laioffer.fastdelivery.model.UserRole;
import com.laioffer.fastdelivery.repository.AuthorityRepository;
import com.laioffer.fastdelivery.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {
   private final UserRepository userRepository;
   private final AuthorityRepository authorityRepository;
   private final PasswordEncoder passwordEncoder;

   public RegisterService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.authorityRepository = authorityRepository;
      this.passwordEncoder = passwordEncoder;
   }
   @Transactional(isolation = Isolation.SERIALIZABLE)
   public void add(User user, UserRole userRole) {
      if(userRepository.existsById(user.getUsername())) {
         throw new UserAlreadyExistException("User already exists");
      }

      user.setPassword(passwordEncoder.encode(user.getPassword()));
      //user.setEnabled(true);
      user.setEnabled(false);
      // We need email authentication

      userRepository.save(user);
      authorityRepository.save(new Authority(user.getUsername(), userRole.name()));
   }

   @Transactional(isolation = Isolation.SERIALIZABLE)
   public void setUserEnabled(User user, boolean enabled) {
      if(!userRepository.existsById(user.getUsername())) {
         throw new UserNotExistException("User doesn't exist!");
      }

      user.setEnabled(enabled);
      userRepository.save(user);
   }
}
