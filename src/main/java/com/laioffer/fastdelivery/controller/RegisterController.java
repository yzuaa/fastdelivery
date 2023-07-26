package com.laioffer.fastdelivery.controller;

import com.laioffer.fastdelivery.model.User;
import com.laioffer.fastdelivery.model.UserRole;
import com.laioffer.fastdelivery.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController{
   private final RegisterService registerService;

   public RegisterController(RegisterService registerService) {
      this.registerService = registerService;
   }
   @PostMapping("/register/guest")
   public void addGuest(@RequestBody User user) {
      registerService.add(user, UserRole.ROLE_GUEST);
   }

   @PostMapping("/register/host")
   public void addHost(@RequestBody User user) {
      registerService.add(user, UserRole.ROLE_HOST);
   }
}
