package com.example.EcommerceJWT.ExceptionHandler;

public class ProductException extends Exception{
    private static final int id = 1;
    private String errorMessage;

    public ProductException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
