package com.company.entity;

import com.company.enums.SubjectStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "subject")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer duration;
    @Column(nullable =false)
    @Enumerated(EnumType.STRING)
    private SubjectStatus status;
    @Column(nullable = false)
    private Double price;

}
