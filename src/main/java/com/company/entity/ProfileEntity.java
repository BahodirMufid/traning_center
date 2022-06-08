package com.company.entity;

import com.company.enums.ProfileStatus;
import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProfileRole role = ProfileRole.STUDENT;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProfileStatus status = ProfileStatus.ACTIVE;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

}
