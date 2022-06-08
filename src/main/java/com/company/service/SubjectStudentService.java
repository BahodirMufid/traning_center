package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.StudentSubjectCreateDTO;
import com.company.dto.StudentSubjectDTO;
import com.company.dto.SubjectDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.StudentSubjectEntity;
import com.company.entity.SubjectEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.enums.StudentSubjectStatus;
import com.company.enums.SubjectStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.repository.StudentSubjectRepository;
import com.company.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectStudentService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;


    public StudentSubjectDTO create(StudentSubjectCreateDTO dto) {

        Integer studentId = dto.getStudentId();
        Integer subjectId = dto.getSubjectId();

        Optional<ProfileEntity> optional = profileRepository.findById(studentId);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("This student not fount");
        }

        ProfileEntity profileEntity = optional.get();
        if (!profileEntity.getStatus().equals(ProfileStatus.ACTIVE)){
            throw new BadRequestException("student No access");
        }

        if (!profileEntity.getRole().equals(ProfileRole.STUDENT)){
            throw new BadRequestException("student No access");
        }

        Optional<SubjectEntity> optional1 = subjectRepository.findById(subjectId);
        if (optional1.isEmpty()){
            throw new ItemNotFoundException("This subject not fount");
        }

        SubjectEntity subjectEntity = optional1.get();
        if (subjectEntity.getStatus().equals(SubjectStatus.BLOCKED)){
            throw new BadRequestException("subject No access");
        }

        StudentSubjectEntity studentSubjectEntity = new StudentSubjectEntity();
        studentSubjectEntity.setSubjectEntity(subjectEntity);
        studentSubjectEntity.setProfileEntity(profileEntity);

        studentSubjectRepository.save(studentSubjectEntity);

        StudentSubjectDTO studentSubjectDTO = new StudentSubjectDTO();
        studentSubjectDTO.setId(studentSubjectEntity.getId());
        studentSubjectDTO.setStatus(studentSubjectEntity.getStatus());

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setPhone(profileEntity.getPhone());
        profileDTO.setName(profileEntity.getName());
        profileDTO.setSurname(profileEntity.getSurname());

        studentSubjectDTO.setStudentDTO(profileDTO);

        SubjectDTO subjectDTO=  new SubjectDTO();
        subjectDTO.setDuration(subjectEntity.getDuration());
        subjectDTO.setPrice(subjectEntity.getPrice());
        subjectDTO.setName(subjectEntity.getName());
        subjectDTO.setId(subjectEntity.getId());

        studentSubjectDTO.setSubjectDTO(subjectDTO);

        return studentSubjectDTO;
    }
}
