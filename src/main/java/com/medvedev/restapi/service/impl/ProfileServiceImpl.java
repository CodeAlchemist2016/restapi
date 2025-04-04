package com.medvedev.restapi.service.impl;

import com.medvedev.restapi.dto.ProfileDTO;
import com.medvedev.restapi.entity.ProfileEntity;
import com.medvedev.restapi.repository.ProfileRepository;
import com.medvedev.restapi.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    /**
     * It will save the user details to databae
     * @param profileDTO - profileDTO
     * @return profileDTO
     */

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        profileEntity = profileRepository.save(profileEntity);
        log.info("Printing the profile entity details {}", profileEntity);
        return mapToProfileDTO(profileEntity);
    }

    /**
     * Mapper method to map values from profile entity to profile DTO
     * @param profileEntity - profileEntity
     * @return profileDTO
     */

    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity,
                ProfileDTO.class);
    }

    /**
     * Mapper method to map values from profile DTO to profile entity
     * @param profileDTO - profileDTO
     * @return profileEntity
     */

    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO,
                ProfileEntity.class);
    }
}
