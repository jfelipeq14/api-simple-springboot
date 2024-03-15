package com.example.crudrapido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.crudrapido.entity.Student;
import com.example.crudrapido.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping({ "/students", "/" })
    public String listStudents(Model model) {
        try {
            Student student = new Student();
            model.addAttribute("student", student); // model para crear estudiante

            model.addAttribute("students", studentService.getStudents()); // obtener todos los estudiantes

            return "students"; // nos retorna al archivo students
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }

    @PostMapping("/students")
    public String saveOrUpdateStudent(@ModelAttribute("student") Student student) {
        try {
            Student studentExist = studentService.getStudent(student.getId());
            if (studentExist.getId() != null) {
                studentExist.setDocument(student.getDocument());
                studentExist.setFirstName(student.getFirstName());
                studentExist.setLastName(student.getLastName());
                studentExist.setEmail(student.getEmail());

                studentService.saveOrUpdate(studentExist);
            } else {
                studentService.saveOrUpdate(student);
            }

            return "redirect:/students";
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }

    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        try {
            Student student = studentService.getStudent(id);
            model.addAttribute("student", student);
            return "students";
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }

    @PostMapping("/students/search")
    public String searchStudent(@ModelAttribute("student") Student student, Model model) {
        Student studentEmpty = new Student();
        try {
            Student findStudent = studentService.getStudentByDocument(student.getDocument());
            if (findStudent != studentEmpty) {
                model.addAttribute("student", findStudent);
                return "students";
            } else {
                return "students";
            }
        } catch (Exception e) {
            // return ("Error: " + e);
            return "students";
        }
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        try {
            studentService.delete(id);
            return "redirect:/students";
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }
}
