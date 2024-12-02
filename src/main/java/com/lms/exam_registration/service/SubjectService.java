package com.lms.exam_registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.exam_registration.entity.Subject;
import com.lms.exam_registration.exception.ResourceNotFoundException;
import com.lms.exam_registration.repository.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    //create a new subject
    public Subject addSubject(Subject subject){
        return subjectRepository.save(subject);
    }
    
    //Retrive subject by ID
    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject Not Found with Id"));
    }

    //Retrive all subjects
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    //Delete a subject by id
    public void deleteSubject(Long id){
        if(!subjectRepository.existsById(id)){
            throw new ResourceNotFoundException("Subject Not Found with Id");
        }
        subjectRepository.deleteById(id);
    }
}
