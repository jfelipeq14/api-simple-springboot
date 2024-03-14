package com.example.crudrapido.service;

import java.util.ArrayList;
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
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            List<Student> listStudent = new ArrayList<Student>();
            return listStudent;
        }
    }

    public Student getStudent(Long id) {
        try {
            return studentRepository.findById(id).get();
        } catch (Exception e) {
            var student = new Student();
            return student;
        }
    }

    public Student getStudentByDocument(String document) {
        try {
            return studentRepository.findByDocument(document);
        } catch (Exception e) {
            var student = new Student();
            return student;
        }
    }

    public void saveOrUpdate(Student student) {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
