package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String jwt) {
        Integer pId = JwtUtil.decode(jwt, ProfileRole.ADMIN);
        ProfileDTO profileDTO = profileService.create(dto);
        return ResponseEntity.ok(profileDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id,
                                             @RequestBody ProfileDTO dto,
                                             @RequestHeader("Authorization") String jwt) {
        JwtUtil.decode(jwt, ProfileRole.ADMIN);
        profileService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/detail/update")
    public ResponseEntity<ProfileDTO> detailUpdate(@RequestBody ProfileDTO dto,
                                                   @RequestHeader("Authorization") String jwt) {
        Integer pId = JwtUtil.decode(jwt);
        profileService.update(pId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> get(@RequestHeader("Authorization") String jwt){

        Integer profileId = JwtUtil.decode(jwt);
        ProfileEntity profileEntity = profileService.get(profileId);

        return ResponseEntity.ok(profileEntity);
    }

}
