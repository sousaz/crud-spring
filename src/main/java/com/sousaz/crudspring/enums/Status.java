package com.sousaz.crudspring.enums;

public enum Status {
    ACTIVE("active"), INACTIVE("incative");

    private String value;

    private Status(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return value;
    }
}
