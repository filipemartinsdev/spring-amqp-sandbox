package com.ms.auth.infra.persistence;

import com.ms.auth.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, UUID> {
}
