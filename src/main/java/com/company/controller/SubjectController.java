package com.company.controller;

import com.company.dto.SubjectDTO;
import com.company.enums.ProfileRole;
import com.company.service.SubjectService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestHeader("Authorization") String jwt,
                                    @RequestBody SubjectDTO subjectDTO){

        JwtUtil.decode(jwt, ProfileRole.ADMIN);
        SubjectDTO dto = subjectService.create(subjectDTO);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String jwt,
                                    @RequestBody SubjectDTO subjectDTO,
                                    @PathVariable("id") Integer id ){

        JwtUtil.decode(jwt,ProfileRole.ADMIN);
        SubjectDTO dto = subjectService.update(subjectDTO, id);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@RequestHeader("Authorization") String jwt,
                                     @PathVariable("id") Integer id){

        JwtUtil.decode(jwt,ProfileRole.ADMIN);
        subjectService.deleted(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> all(){

        List<SubjectDTO> dtos = subjectService.allSubject();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){

        SubjectDTO dto = subjectService.getSubject(id);
        return ResponseEntity.ok(dto);
    }


}
