package com.fai.usuario.infrastructure.exeptions;

public class ResourceNotFoundExcepton extends RuntimeException{

    public ResourceNotFoundExcepton(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundExcepton(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
