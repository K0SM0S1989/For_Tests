package com.testtask.demo.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsNotFoundException extends Exception{
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

}
