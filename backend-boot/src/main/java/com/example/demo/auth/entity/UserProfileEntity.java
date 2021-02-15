package com.example.demo.auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    private String userId;
    private String name;
}
