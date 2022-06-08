package com.company.entity;

import com.company.entity.StudentSubjectEntity;
import com.company.entity.TeacherSubjectEntity;
import com.company.enums.GroupStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(nullable = false, name = "teacher_subject_id")
    @OneToOne(fetch = FetchType.LAZY)
    private TeacherSubjectEntity teacherSubjectEntity;

    @JoinColumn(nullable = false, name = "student_subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private StudentSubjectEntity studentSubjectEntity;

    @Column(nullable = false,name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


    @Column(nullable = false,name = "returned_date")
    private LocalDateTime returnedDate;

    @Column(nullable = false, name = "status")
    private GroupStatus groupStatus = GroupStatus.ACTIVE;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

}
