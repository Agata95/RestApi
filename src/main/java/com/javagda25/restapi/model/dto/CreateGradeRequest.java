package com.javagda25.restapi.model.dto;

import com.javagda25.restapi.model.GradeSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGradeRequest {
    private Long id;

    @Enumerated(EnumType.STRING)
    private GradeSubject subject;

    private double value;
}
