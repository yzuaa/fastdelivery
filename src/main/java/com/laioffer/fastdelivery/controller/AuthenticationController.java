package com.laioffer.fastdelivery.controller;

import org.springframework.web.bind.annotation.RestController;
import com.laioffer.fastdelivery.model.Token;
import com.laioffer.fastdelivery.model.User;
import com.laioffer.fastdelivery.model.UserRole;
import com.laioffer.fastdelivery.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthenticationController {
   private final AuthenticationService authenticationService;

   public AuthenticationController(AuthenticationService authenticationService) {
      this.authenticationService = authenticationService;
   }

   @PostMapping("/authenticate/guest")
   public Token authenticateGuest(@RequestBody User user) {
      return authenticationService.authenticate(user, UserRole.ROLE_GUEST);
   }

   @PostMapping("/authenticate/host")
   public Token authenticateHost(@RequestBody User user) {
      return authenticationService.authenticate(user, UserRole.ROLE_HOST);
   }
}

