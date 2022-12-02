package com.example.caderno.exception;

public class BadRequestException extends RuntimeException {

    BadRequestException(String message){
        super(message);
    }

    BadRequestException(){
        super();
    }
}
