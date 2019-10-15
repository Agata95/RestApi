package com.javagda25.restapi.service;

import com.javagda25.restapi.mapper.GradeMapper;
import com.javagda25.restapi.model.Grade;
import com.javagda25.restapi.model.dto.CreateGradeRequest;
import com.javagda25.restapi.model.dto.GradeUpdateRequest;
import com.javagda25.restapi.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeMapper gradeMapper;

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public Long save(CreateGradeRequest dto) {
        Grade grade = gradeMapper.createGradeFromDto(dto);

        return gradeRepository.save(grade).getId();
    }

    public Grade getById(Long gradeId) {
        Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);

        if (gradeOptional.isPresent()) {
            return gradeOptional.get();
        }
        throw new EntityNotFoundException("grade, id: " + gradeId);
    }

    public void update(GradeUpdateRequest gradeToEdit) {
        Optional<Grade> gradeOptional = gradeRepository.findById(gradeToEdit.getGradeId());
        if (gradeOptional.isPresent()) {
            Grade grade = gradeOptional.get();

            if (gradeToEdit.getSubject() != null) {
                grade.setSubject(gradeToEdit.getSubject());
            }
            if (gradeToEdit.getValue() != null) {
                grade.setValue(gradeToEdit.getValue());
            }

            gradeRepository.save(grade);
            return;
        }
        throw new EntityNotFoundException("grade, id: " + gradeToEdit.getGradeId());
    }

    public void delete(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("grade, id: " + id);
    }
}
