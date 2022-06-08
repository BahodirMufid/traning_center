package com.company.entity;

import com.company.enums.TeacherSubjectStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "teacher_subject")
public class TeacherSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TeacherSubjectStatus status;

    @JoinColumn(nullable = false, name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profileEntity;

    @JoinColumn(nullable = false, name = "subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubjectEntity subjectEntity;

    @Column(nullable = false)
    private Boolean visible=  Boolean.TRUE;

//    @Column(nullable = false, name = "created_date")
//    private LocalDateTime createdDate = LocalDateTime.now();
//
//    @Column(nullable = false, name = "returned_date")
//    private LocalDateTime returnedDate;
}
