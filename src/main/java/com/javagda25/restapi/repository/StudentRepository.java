package com.javagda25.restapi.repository;

import com.javagda25.restapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findBySurname(String surname);
    List<Student> findByAgeBefore(Integer age);
    List<Student> findByAgeAfter(Integer age);
    List<Student> findByAgeBetween(Integer ageBefore, Integer ageAfter);
//    List<Student> findAllBySurname(String surname, Pageable pageable);
}
