package com.ust.order_management_service.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String s){
        super(s);
    }
}
