package com.velocia.accountapi.repository;

import com.velocia.accountapi.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsById(String id);

    AccountEntity getById(String id);
}
