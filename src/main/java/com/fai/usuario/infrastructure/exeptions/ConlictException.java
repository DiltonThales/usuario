package com.fai.usuario.infrastructure.exeptions;

public class ConlictException extends RuntimeException{

    public ConlictException(String mensagem){
        super(mensagem);
    }

    public ConlictException(String mensagem, Throwable throwable){
        super(mensagem);
    }

}
