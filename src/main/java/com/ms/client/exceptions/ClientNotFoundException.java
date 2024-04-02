package com.ms.client.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message){
        super(message);
    }
}
