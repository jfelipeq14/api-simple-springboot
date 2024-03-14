package com.example.crudrapido.repository;

import com.example.crudrapido.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM tbl_student WHERE document=?", nativeQuery = true)
    Student findByDocument(String document);
}
