package com.medvedev.restapi.repository;

import com.medvedev.restapi.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

}
