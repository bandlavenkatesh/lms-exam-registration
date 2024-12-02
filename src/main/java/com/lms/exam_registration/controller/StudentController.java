package com.lms.exam_registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.exam_registration.entity.Student;
import com.lms.exam_registration.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //create a new student
    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    //Retrive a student by ID
    @GetMapping("/{id}")
    public Student getStudentbyId(@PathVariable Long id){
        return studentService.getStudentById(id);

    }

    //Retrive all Students
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    //Delete student by ID
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "Student deleted successfully!";
    }

    @PostMapping("/{studentId}/enroll/{subjectId}")
    public String enrollStudentInSubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return studentService.enrollStudentInSubject(studentId, subjectId);
    } 
    
}
