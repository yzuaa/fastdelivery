package com.laioffer.fastdelivery.exception;

public class UserAlreadyExistException extends RuntimeException{
   public UserAlreadyExistException(String message) {
      super(message);
   }
}
