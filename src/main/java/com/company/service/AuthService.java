package com.company.service;

import com.company.dto.AuthDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.RegistrationDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO login(AuthDTO authDTO) {

        Optional<ProfileEntity> optional = profileRepository.findByLogin(authDTO.getLogin());
        if (optional.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        ProfileEntity profile = optional.get();
        if (!profile.getPassword().equals(authDTO.getPassword())) {
            throw new BadRequestException("User not found");
        }

        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new BadRequestException("No ruxsat");
        }

        ProfileDTO dto = new ProfileDTO();
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setPhone(profile.getPhone());
        dto.setJwt(JwtUtil.encode(profile.getId(), profile.getRole()));

        return dto;
    }

    public ProfileDTO registration(RegistrationDTO dto) {
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

        // name; surname; login; password; phone;

        ProfileDTO responseDTO = new ProfileDTO();
        responseDTO.setPhone(dto.getPhone());
        responseDTO.setName(dto.getName());
        responseDTO.setSurname(dto.getSurname());
        responseDTO.setLogin(dto.getLogin());
        responseDTO.setJwt(JwtUtil.encode(entity.getId(), entity.getRole()));

        return responseDTO;
    }
}
