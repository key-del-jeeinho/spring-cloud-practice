package com.velocia.accountapi.data.request;

public record RegisterRequest(
        String name,
        String id,
        String rawPassword,
        Integer age
) {
}
