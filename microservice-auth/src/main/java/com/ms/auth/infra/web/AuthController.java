package com.ms.auth.infra.web;

import com.ms.auth.application.dto.CreateUserRequest;
import com.ms.auth.application.dto.UpdateUserCredentialsRequest;
import com.ms.auth.application.dto.UserCredentialsDTO;
import com.ms.auth.services.UserCredentialsService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserCredentialsService userCredentialsService;

    public AuthController(UserCredentialsService userCredentialsService) {
        this.userCredentialsService = userCredentialsService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(@Valid @RequestBody CreateUserRequest request) {
        userCredentialsService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserCredentialsDTO>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCredentialsService.getAll());
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserCredentialsDTO> update(
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateUserCredentialsRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCredentialsService.update(userId, request));
    }
}
