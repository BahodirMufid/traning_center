package com.company.repository;

import com.company.entity.SubjectEntity;
import com.company.enums.SubjectStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Integer> {

   List<SubjectEntity> findAllByStatus(SubjectStatus status);

}
