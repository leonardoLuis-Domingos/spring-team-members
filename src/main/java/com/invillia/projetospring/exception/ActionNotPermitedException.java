package com.invillia.projetospring.exception;

public class ActionNotPermitedException extends RuntimeException{

    public ActionNotPermitedException(final String message) {
        super("Ação não permitida.");
    }
}
