package com.company.dto;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    
    
    
    
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String login;
    private String password;
    private ProfileRole role;
    private ProfileStatus status;

    private String jwt;

}
