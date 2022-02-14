package com.velocia.accountapi.data.entity;

import com.velocia.accountapi.data.dto.AccountDto;
import lombok.*;

import javax.persistence.*;

@Table(name = "account")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    private String name;
    private Integer age;
    private String id;
    private String encodedPassword;

    public AccountDto toDto() {
        return new AccountDto(uuid, name, age, id, encodedPassword);
    }
}
