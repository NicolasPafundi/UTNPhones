package com.utn.TPFinal.exceptions;

public class InvalidLoginException extends Throwable {
    public String getMessage() {
        return "Invalid login";
    }
}
