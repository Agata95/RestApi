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
public class GradeUpdateRequest {

    private Long gradeId;

    @Enumerated(EnumType.STRING)
    private GradeSubject subject;

    private Double value;
}
