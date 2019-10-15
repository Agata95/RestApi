package com.javagda25.restapi.controller;

import com.javagda25.restapi.model.Student;
import com.javagda25.restapi.model.dto.CreateStudentRequest;
import com.javagda25.restapi.model.dto.StudentUpdateRequest;
import com.javagda25.restapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

//    @RequestBody - jeśli coś ma przyjść w Json, to trezba to dopisać
    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long studentId) {
        return studentService.getById(studentId);
    }

    @PutMapping
    public Long putStudent(@RequestBody CreateStudentRequest student) {
        return studentService.save(student);
    }

    //    edycja
    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postStudent(@RequestBody StudentUpdateRequest student) {
        studentService.update(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    //    pobierz studentów po nazwisku
    @GetMapping("/surname/{surname}")
    public List<Student> getBySurname(@PathVariable("surname") String surname) {
        return studentService.getBySurname(surname);
    }

    //    pobierz studenta po wieku (od)
    @GetMapping("/ageBefore/{age}")
    public List<Student> getByAgeBefore(@PathVariable("age") Integer age) {
        return studentService.getByAgeBefore(age);
    }


    //    pobierz studenta po wieku (do)
    @GetMapping("/ageAfter/{age}")
    public List<Student> getByAgeAfter(@PathVariable("age") Integer age) {
        return studentService.getByAgeAfter(age);
    }


    //    pobierz studenta po wieku (od - do)
    @GetMapping("/ageBetween/{age}")
    public List<Student> getByAgeBetween(@PathVariable("age") Integer ageBefore, Integer ageAfter) {
        return studentService.getByAgeBeforeAndAfter(ageBefore, ageAfter);
    }

    //    stwórz mapping zwracający listę użytkowników. Paginuj strony (Paginated)
    @GetMapping("/getPage")
    public Page<Student> getPage(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "5") int size) {
        return studentService.getPage(PageRequest.of(page, size));
    }
}
