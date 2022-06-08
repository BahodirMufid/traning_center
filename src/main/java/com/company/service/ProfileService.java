package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    // admin
    public ProfileDTO create(ProfileDTO dto) {
        // name; surname; login; password; phone;
        Optional<ProfileEntity> optional = profileRepository.findByLogin(dto.getLogin());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());

        entity.setRole(ProfileRole.STUDENT);

        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public void update(Integer profileId, ProfileDTO dto) {

        ProfileEntity entity = get(profileId);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());

        profileRepository.save(entity);
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Profile Not found");
        });
    }

}
