package com.laioffer.fastdelivery.exception;

public class UserNotExistException extends RuntimeException {
   public UserNotExistException(String message) {
      super(message);
   }
}

