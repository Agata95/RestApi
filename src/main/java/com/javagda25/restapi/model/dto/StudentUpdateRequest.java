package com.javagda25.restapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javagda25.restapi.model.Grade;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateRequest {

//    to co chcemy aby było update'owane w klasie Student:

    private Long studentId;

    private String name;
    private String surname;
    private Boolean isAlive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateOfBirth;


}
