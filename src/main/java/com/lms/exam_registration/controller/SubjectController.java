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

import com.lms.exam_registration.entity.Subject;
import com.lms.exam_registration.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //create a new subject
    @PostMapping
    public Subject addStudent(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }

    //Retrive Subject by Id
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }

    //retrive All subjects
    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    //Delete Subject by Id
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
        return "Subject deleted sucessfully!";
    }
    
}
