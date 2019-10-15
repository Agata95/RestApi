package com.javagda25.restapi.mapper;

import com.javagda25.restapi.model.Student;
import com.javagda25.restapi.model.dto.CreateStudentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {


    Student createStudentFromDto(CreateStudentRequest dto);
}
