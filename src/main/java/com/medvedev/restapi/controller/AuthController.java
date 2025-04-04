package com.medvedev.restapi.controller;

import com.medvedev.restapi.dto.ProfileDTO;
import com.medvedev.restapi.io.ProfileRequest;
import com.medvedev.restapi.io.ProfileResponse;
import com.medvedev.restapi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final ProfileService profileService;

    /**
     * API endpoint to register new user
     * @param profileRequest - profileRequest
     * @return profileResponse
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        log.info("API /register is called {}",
                profileRequest);
        ProfileDTO profileDTO = mapToProfileDTO(profileRequest);
        profileDTO = profileService.createProfile(profileDTO);
        log.info("Printing the profile DTO details {}", profileDTO);
        return mapToProfileResponse(profileDTO);
    }

    /**
     * Mapper method to map values from profile request to profile DTO
     * @param profileRequest - profileRequest
     * @return profileDTO
     */

    private ProfileDTO mapToProfileDTO(@Valid ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    /**
     * Mapper method to map values from profile DTO to profile response
     * @param profileDTO - profileDTO
     * @return profileResponse
     */

    private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileResponse.class);
    }
}
