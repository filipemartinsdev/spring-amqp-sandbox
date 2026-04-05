package com.ms.user.services;

import com.ms.user.application.dto.UserCredentialsDTO;
import com.ms.user.application.dto.UserProfileResponse;
import com.ms.user.domain.UserProfile;
import com.ms.user.infra.persistence.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public void createByUserCredentials(UserCredentialsDTO userCredentials){
        var userProfile = new UserProfile();
        userProfile.setId(userCredentials.id());
        userProfile.setEmail(userCredentials.email());
        userProfile.setUsername(userCredentials.username());
        userProfile.setBio("");
        userProfileRepository.save(userProfile);
    }

    public List<UserProfileResponse> getAll(){
        return userProfileRepository.findAll()
                .stream()
                .map(userProfile -> new UserProfileResponse(
                        userProfile.getId(),
                        userProfile.getUsername(),
                        userProfile.getEmail(),
                        userProfile.getBio()
                ))
                .toList();
    }

    public void updateFromCredentials(UserCredentialsDTO userCredentialsDTO) {
        var userProfile = userProfileRepository.findById(userCredentialsDTO.id())
                .orElseThrow(() -> new RuntimeException("UserProfile Not Found"));

        userProfile.setUsername(userCredentialsDTO.username());
        userProfile.setEmail(userCredentialsDTO.email());

        userProfileRepository.save(userProfile);

        log.info("UserProfile Updated: {}", userProfile);
    }
}
