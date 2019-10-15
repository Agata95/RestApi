package com.javagda25.restapi.controller;

import com.javagda25.restapi.model.Grade;
import com.javagda25.restapi.model.dto.CreateGradeRequest;
import com.javagda25.restapi.model.dto.GradeUpdateRequest;
import com.javagda25.restapi.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping
    public List<Grade> getGradeList() {
        return gradeService.getAll();
    }

    @PutMapping
    public Long putStudent(CreateGradeRequest gradeRequest) {
        return gradeService.save(gradeRequest);
    }

    @GetMapping("/{id}")
    public Grade getById(@PathVariable("id") Long gradeId) {
        return gradeService.getById(gradeId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postGrade(GradeUpdateRequest grade) {
        gradeService.update(grade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteGrade(@PathVariable("id") Long id) {
        gradeService.delete(id);
    }

}
