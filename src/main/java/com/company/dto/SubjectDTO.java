package com.company.dto;

import com.company.enums.SubjectStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDTO {

    private Integer id;
    private String name;
    private Integer duration;
    private SubjectStatus status;
    private Double price;

}
