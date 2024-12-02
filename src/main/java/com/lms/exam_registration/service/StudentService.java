package com.lms.exam_registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.exam_registration.entity.Student;
import com.lms.exam_registration.entity.Subject;
import com.lms.exam_registration.exception.BadRequestException;
import com.lms.exam_registration.exception.ResourceNotFoundException;
import com.lms.exam_registration.repository.StudentRepository;
import com.lms.exam_registration.repository.SubjectRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    //create a new student
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    //Retrive a student by ID
    public Student getStudentById(Long id){
        return studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student Not Found with Id"));
    }

    //Retrive all Students
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //Delete a Student by Id
    public void deleteStudent(Long id){
        if(!studentRepository.existsById(id)){
            throw new ResourceNotFoundException("Student not Found with Id");
        }

        studentRepository.deleteById(id);
    }
    
    public String enrollStudentInSubject(Long studentId, Long subjectId){
        //Fetch Student
        Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Student not found with Id"));

        //Fetch subject
        Subject subject = subjectRepository.findById(subjectId)
                        .orElseThrow(() -> new ResourceNotFoundException("Subject not found with Id"));

        //check if already enrolled
       if(student.getEnrolledSubjects().contains(subject)){
            throw new BadRequestException("Student is already enrolled in this subject");
       }

       //Enroll student in the subject
       student.getEnrolledSubjects().add(subject);
       studentRepository.save(student);

       return "Student enrolled in the subject successfully!";
    }

}
