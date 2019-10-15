package com.javagda25.restapi.controller;

import com.javagda25.restapi.model.Student;
import com.javagda25.restapi.model.dto.CreateStudentRequest;
import com.javagda25.restapi.model.dto.StudentUpdateRequest;
import com.javagda25.restapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudentList() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long studentId) {
        return studentService.getById(studentId);
    }

    @PutMapping
    public Long putStudent(CreateStudentRequest student) {
        return studentService.save(student);
    }

    //    edycja
    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postStudent(StudentUpdateRequest student) {
        studentService.update(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

}
