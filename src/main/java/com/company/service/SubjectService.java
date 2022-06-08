package com.company.service;

import com.company.dto.SubjectDTO;
import com.company.entity.SubjectEntity;
import com.company.enums.SubjectStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.exp.NullFeildException;
import com.company.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectDTO create(SubjectDTO subjectDTO) {

        isValid(subjectDTO);

        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setDuration(subjectDTO.getDuration());
        subjectEntity.setName(subjectDTO.getName());
        subjectEntity.setPrice(subjectDTO.getPrice());
        subjectEntity.setStatus(SubjectStatus.ACTIVE);

        subjectRepository.save(subjectEntity);
        subjectDTO.setId(subjectEntity.getId());

        return subjectDTO;
    }

    private void isValid(SubjectDTO subjectDTO) {

        if (subjectDTO.getName().isEmpty() || subjectDTO.getName() == null){
            throw new NullFeildException("subject's name mustn't be empty");
        }

        if (subjectDTO.getDuration() < 0 || subjectDTO.getDuration() == null){
            throw new NullFeildException("subject duration wrong");
        }

        if (subjectDTO.getPrice() < 0 || subjectDTO.getPrice() == null){
            throw new NullFeildException("subject price wrong");
        }
    }

    public SubjectDTO update(SubjectDTO subjectDTO, Integer id) {

        Optional<SubjectEntity> optional = subjectRepository.findById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("subject not fount");
        }

        SubjectEntity subjectEntity = optional.get();
        isValid(subjectDTO);

        subjectEntity.setName(subjectDTO.getName());
        subjectEntity.setDuration(subjectDTO.getDuration());
        subjectEntity.setPrice(subjectDTO.getPrice());

        subjectRepository.save(subjectEntity);

        subjectDTO.setId(subjectEntity.getId());
        return subjectDTO;
    }

    public void deleted(Integer id) {

        Optional<SubjectEntity> optional = subjectRepository.findById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("subject not fount");
        }

        SubjectEntity subjectEntity = optional.get();
        if (subjectEntity.getStatus().equals(SubjectStatus.BLOCKED)) {
            throw new BadRequestException("This subject already blocked");
        }

        subjectEntity.setStatus(SubjectStatus.BLOCKED);
        subjectRepository.save(subjectEntity);
    }

    public List<SubjectDTO> allSubject() {

        List<SubjectEntity> allByStatus = subjectRepository.findAllByStatus(SubjectStatus.ACTIVE);

        List<SubjectDTO> dtos = new ArrayList<>();

        allByStatus.forEach(subjectEntity -> {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setName(subjectEntity.getName());
            subjectDTO.setDuration(subjectEntity.getDuration());
            subjectDTO.setPrice(subjectEntity.getPrice());
            subjectDTO.setId(subjectEntity.getId());

            dtos.add(subjectDTO);
        });

        return dtos;
    }

    public SubjectDTO getSubject(Integer id) {

        Optional<SubjectEntity> optional = subjectRepository.findById(id);

        if (optional.isEmpty()){
            throw new ItemNotFoundException("This subject not found");
        }

        SubjectEntity subjectEntity = optional.get();
        if (!subjectEntity.getStatus().equals(SubjectStatus.ACTIVE)){
            throw new BadRequestException("No access");
        }

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subjectEntity.getId());
        subjectDTO.setName(subjectEntity.getName());
        subjectDTO.setPrice(subjectEntity.getPrice());
        subjectDTO.setDuration(subjectEntity.getDuration());

        return subjectDTO;
    }
}
