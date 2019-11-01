package com.invillia.projetospring.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(final String message) {
        super("Usuário não encontrado, ID: " + message);
    }
}
