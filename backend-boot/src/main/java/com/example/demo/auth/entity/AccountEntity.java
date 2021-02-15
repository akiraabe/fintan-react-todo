package com.example.demo.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    private String userId;

    private String password;
}
