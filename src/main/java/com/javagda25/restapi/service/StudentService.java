package com.javagda25.restapi.service;

import com.javagda25.restapi.mapper.StudentMapper;
import com.javagda25.restapi.model.Student;
import com.javagda25.restapi.model.dto.CreateStudentRequest;
import com.javagda25.restapi.model.dto.StudentUpdateRequest;
import com.javagda25.restapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
//  getOne nie pobiera relacji, nie pobiorę ocen studenta
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        }
        throw new EntityNotFoundException("student, id: " + studentId);
    }

    //    tworzenie i nadpisywanie
    public Long save(CreateStudentRequest dto) {
        Student student = studentMapper.createStudentFromDto(dto);

        return studentRepository.save(student).getId();
    }

    public void update(StudentUpdateRequest studentToEdit) {
        Optional<Student> studentOptional = studentRepository.findById(studentToEdit.getStudentId());
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (studentToEdit.getName() != null) {
                student.setName(studentToEdit.getName());
            }
            if (studentToEdit.getSurname() != null) {
                student.setSurname(studentToEdit.getSurname());
            }
            if (studentToEdit.getDateOfBirth() != null) {
                student.setDateOfBirth(studentToEdit.getDateOfBirth());
            }
            if (studentToEdit.getIsAlive() != null) {
                student.setAlive(studentToEdit.getIsAlive());
            }

            studentRepository.save(student);
            return;
        }
        throw new EntityNotFoundException("student, id: " + studentToEdit.getStudentId());
    }

    public void delete(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("student, id: " + id);
    }

    public List<Student> getBySurname(String surname) {
        return studentRepository.findBySurname(surname);
    }

    public List<Student> getByAgeBefore(Integer age) {
        return studentRepository.findByAgeBefore(age);
    }

    public List<Student> getByAgeAfter(Integer age) {
        return studentRepository.findByAgeAfter(age);
    }

    public List<Student> getByAgeBeforeAndAfter(Integer ageBefore, Integer ageAfter) {
        return studentRepository.findByAgeBetween(ageBefore, ageAfter);
    }

    public Page<Student> getPage(PageRequest of){
        return studentRepository.findAll(of);
    }
}
