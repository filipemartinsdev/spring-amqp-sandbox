package com.ms.user.infra.web;

import com.ms.user.application.dto.UserProfileResponse;
import com.ms.user.services.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<List<UserProfileResponse>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileService.getAll());
    }
}
