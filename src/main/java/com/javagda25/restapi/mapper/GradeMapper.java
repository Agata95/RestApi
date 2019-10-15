package com.javagda25.restapi.mapper;

import com.javagda25.restapi.model.Grade;
import com.javagda25.restapi.model.dto.CreateGradeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    Grade createGradeFromDto(CreateGradeRequest dto);
}
