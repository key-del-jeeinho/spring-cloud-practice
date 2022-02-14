package com.velocia.accountapi.service;

import com.velocia.accountapi.data.dto.AccountDto;

public interface AccountService {
    Long register(final AccountDto dto);

    AccountDto login(final String id, final String rawPassword);
}
