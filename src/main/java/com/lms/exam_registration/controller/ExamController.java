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

import com.lms.exam_registration.entity.Exam;
import com.lms.exam_registration.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    //Create a new exam 
    @PostMapping("/{subjectId}")
    public Exam addExam(@RequestBody Exam exam, @PathVariable Long subjectId){
        return examService.addExam(exam, subjectId);
    }

    //Retrive exam by ID
    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id){
        return examService.getExamById(id);
    }

    //Retrive All exams
    @GetMapping
    public List<Exam> getAllExams(){
        return examService.getAllExams();
    }

    //Delete an Exam by ID
    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Long id){
        examService.deleteExam(id);
        return "Exam deleted successfully!";
    }

    @PostMapping("/{examId}/register/{studentId}")
    public String registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId){
        return examService.registerStudentForExam(examId, studentId);
    }
}
