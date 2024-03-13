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
    public String listStudents(Model modelo) {
        try {
            modelo.addAttribute("students", studentService.getStudents());
            return "students"; // nos retorna al archivo students
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }

    @GetMapping("/students/create")
    public String formCreateStudent(Model modelo) {
        try {
            Student student = new Student();
            modelo.addAttribute("student", student);
            return "crear_student";
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        try {
            studentService.saveOrUpdate(student);
            return "redirect:/students";
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }

    @GetMapping("/students/update/{id}")
    public String formUpdateStudent(@PathVariable Long id, Model modelo) {
        try {
            modelo.addAttribute("student", studentService.getStudent(id));
            return "editar_student";
        } catch (Exception e) {
            return ("Error: " + e);
        }
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student,
            Model modelo) {
        try {
            Student studentExistente = studentService.getStudent(id);
            studentExistente.setStudentId(id);
            studentExistente.setFirstName(student.getFirstName());
            studentExistente.setLastName(student.getLastName());
            studentExistente.setEmail(student.getEmail());

            studentService.saveOrUpdate(studentExistente);
            return "redirect:/students";
        } catch (Exception e) {
            return ("Error: " + e);
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
