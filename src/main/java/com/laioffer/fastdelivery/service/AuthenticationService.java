package com.laioffer.fastdelivery.service;

import org.springframework.stereotype.Service;
import com.laioffer.fastdelivery.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import com.laioffer.fastdelivery.exception.UserNotExistException;
import com.laioffer.fastdelivery.model.Token;
import com.laioffer.fastdelivery.model.User;
import com.laioffer.fastdelivery.model.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class AuthenticationService {

   private final AuthenticationManager authenticationManager;
   private final JwtUtil jwtUtil;

   public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
      this.authenticationManager = authenticationManager;
      this.jwtUtil = jwtUtil;
   }

   public Token authenticate(User user, UserRole role) throws UserNotExistException {
      Authentication auth = null;
      try {
         auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
      } catch (AuthenticationException exception) {
         throw new UserNotExistException("User Doesn't Exist");
      }

      if (auth == null || !auth.isAuthenticated() || !auth.getAuthorities().contains(new SimpleGrantedAuthority(role.name()))) {
         throw new UserNotExistException("User has not permission to access");
      }
      return new Token(jwtUtil.generateToken(user.getUsername()));
   }

}
