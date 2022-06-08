package com.company.controller;

import com.company.dto.StudentSubjectCreateDTO;
import com.company.dto.StudentSubjectDTO;
import com.company.enums.ProfileRole;
import com.company.service.SubjectStudentService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student_subject/")
public class StudentSubjectController {
    @Autowired
    private SubjectStudentService subjectStudentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(StudentSubjectCreateDTO dto,
                                 @RequestHeader("Authorization") String jwt){

        JwtUtil.decode(jwt,ProfileRole.ADMIN);
        StudentSubjectDTO studentSubjectDTO = subjectStudentService.create(dto);
        return ResponseEntity.ok(studentSubjectDTO);
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestHeader("Authorization") String jwt,
                                    @RequestBody Integer subjectId){

        Integer studentId = JwtUtil.decode(jwt, ProfileRole.STUDENT);
        StudentSubjectCreateDTO studentSubjectCreateDTO = new StudentSubjectCreateDTO();
        studentSubjectCreateDTO.setStudentId(studentId);
        studentSubjectCreateDTO.setSubjectId(subjectId);

        StudentSubjectDTO studentSubjectDTO = subjectStudentService.create(studentSubjectCreateDTO);
        return ResponseEntity.ok(studentSubjectDTO);
    }
}