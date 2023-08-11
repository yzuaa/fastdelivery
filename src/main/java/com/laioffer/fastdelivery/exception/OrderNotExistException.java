package com.laioffer.fastdelivery.exception;

public class OrderNotExistException extends RuntimeException {
    public OrderNotExistException(String message) {
        super(message);
    }
}
