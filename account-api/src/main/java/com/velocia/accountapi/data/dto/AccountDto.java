package com.velocia.accountapi.data.dto;

public record AccountDto(Long uuid, String name, Integer age, String id, String password) {
    public AccountDto(String name, Integer age, String id, String password) {
        this(0L, name, age, id, password);
    }
}
