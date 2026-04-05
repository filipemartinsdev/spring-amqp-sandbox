package com.ms.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity @Table(name = "user_profile")
@ToString
@AllArgsConstructor @NoArgsConstructor @Data
public class UserProfile {
    @Id
    public UUID id;

    @Column(unique = true, nullable = false)
    @NotBlank
    public String username;

    @Column(unique = true, nullable = false)
    @Email
    public String email;

    @Column(unique = true, nullable = false)
    public String bio;
}
