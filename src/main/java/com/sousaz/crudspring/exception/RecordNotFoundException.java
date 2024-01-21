package com.sousaz.crudspring.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id){
        super("Resgistro não encontrado com o id: "+id);
    }
}
