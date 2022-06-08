package com.company.dto;

import com.company.entity.ProfileEntity;
import com.company.entity.SubjectEntity;
import com.company.enums.StudentSubjectStatus;
import com.company.enums.TeacherSubjectStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentSubjectDTO {

    private Integer id;
    private StudentSubjectStatus status;

    private ProfileDTO studentDTO;
    private SubjectDTO subjectDTO;
    private Boolean visible;

}
