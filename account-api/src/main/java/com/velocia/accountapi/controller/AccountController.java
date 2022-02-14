package com.velocia.accountapi.controller;

import com.velocia.accountapi.data.dto.AccountDto;
import com.velocia.accountapi.data.request.LoginRequest;
import com.velocia.accountapi.data.request.RegisterRequest;
import com.velocia.accountapi.data.response.LoginResponse;
import com.velocia.accountapi.data.response.RegisterResponse;
import com.velocia.accountapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody final RegisterRequest request) {
        final AccountDto dto = new AccountDto(request.name(), request.age(), request.id(), request.rawPassword());
        final Long uuid = accountService.register(dto);
        final RegisterResponse response = new RegisterResponse(uuid);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest request) {
        final AccountDto dto = accountService.login(request.id(), request.rawPassword());
        final Long uuid = dto.uuid();
        final LoginResponse response = new LoginResponse(uuid);
        return ResponseEntity.ok(response);
    }
}
