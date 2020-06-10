package com.walleftech.csbe.exceptions;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException() {
        super("Senha inválida");
    }
}
