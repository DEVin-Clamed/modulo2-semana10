package com.example.caderno.exception;

public class ServerSideException extends RuntimeException{
    public ServerSideException() {
        super();
    }

    public ServerSideException(String message) {
        super(message);
    }
}
