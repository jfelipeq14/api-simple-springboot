package com.example.crudrapido.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crudrapido.entity.Student;
import com.example.crudrapido.repository.StudentRepository;

@Service

public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public void saveOrUpdate(Student student) {
        studentRepository.save(student);
    }

    public void delete(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
