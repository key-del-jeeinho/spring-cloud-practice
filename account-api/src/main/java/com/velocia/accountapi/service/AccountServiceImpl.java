package com.velocia.accountapi.service;

import com.velocia.accountapi.data.dto.AccountDto;
import com.velocia.accountapi.data.entity.AccountEntity;
import com.velocia.accountapi.exception.UnknownIdException;
import com.velocia.accountapi.exception.WrongPasswordException;
import com.velocia.accountapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long register(AccountDto dto) {
        return saveAndGetUUID(dto);
    }

    @Override
    public AccountDto login(final String id, final String rawPassword) {
        return authorizeAndGetDto(id, rawPassword);
    }

    private Long saveAndGetUUID(AccountDto dto) {
        return accountRepository.save(generateAccount(dto)).getUuid();
    }


    private AccountDto authorizeAndGetDto(String id, String rawPassword) {
        final AccountEntity entity = getAccountById(id);
        checkPassword(entity.getEncodedPassword(), rawPassword);
        return entity.toDto();
    }

    private AccountEntity getAccountById(final String id) {
        if(!accountRepository.existsById(id)) throw new UnknownIdException();
        return accountRepository.getById(id);
    }

    private AccountEntity generateAccount(final AccountDto dto) {
        return AccountEntity.builder()
                .name(dto.name())
                .age(dto.age())
                .id(dto.id())
                .encodedPassword(encode(dto.password()))
                .build();
    }

    private void checkPassword(final String encodedPassword, final String rawPassword) {
        if(!passwordEncoder.matches(rawPassword, encodedPassword)) throw new WrongPasswordException();
    }

    private String encode(final String password) {
        return passwordEncoder.encode(password);
    }
}
