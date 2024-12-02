package com.lms.exam_registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.exam_registration.entity.Exam;
import com.lms.exam_registration.entity.Student;
import com.lms.exam_registration.entity.Subject;
import com.lms.exam_registration.exception.BadRequestException;
import com.lms.exam_registration.exception.ResourceNotFoundException;
import com.lms.exam_registration.repository.ExamRepository;
import com.lms.exam_registration.repository.StudentRepository;
import com.lms.exam_registration.repository.SubjectRepository;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    //create a new Exam
    public Exam addExam(Exam exam, Long subjectId){
        Subject subject = subjectRepository.findById(subjectId)
                            .orElseThrow(() -> new RuntimeException("Subject Not Found"));
        exam.setSubject(subject);
        return examRepository.save(exam);

    }

    //Retrive exam by Id
    public Exam getExamById(Long id){
        return examRepository.findById(id).orElseThrow(() -> new RuntimeException("Exam Not Found"));
    }

    //Retrive All exams
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

    //Delte exam by
    public void deleteExam(Long id){
        if(!examRepository.existsById(id)){
            throw new RuntimeException("Exam Not Found");
        }
        examRepository.deleteById(id);
    }

    public String registerStudentForExam(Long examId, Long StudentID){
        //Fetch exam by ID
        Exam exam = examRepository.findById(examId)
                    .orElseThrow(() -> new ResourceNotFoundException("Exam not found by Id"));

        //Fetch student by ID
        Student student =studentRepository.findById(StudentID)
                        .orElseThrow(() -> new ResourceNotFoundException("Student not found by Id"));

        //Check if student is enrolled in the subject of the exam
        if(!student.getEnrolledSubjects().contains(exam.getSubject())){
            throw new BadRequestException("Student is not enrolled in the subject for this exam");
        }

        //Register Student for exam
        student.getRegisteredExams().add(exam);
        studentRepository.save(student);

        return "Student registered for the exam successfully!";
    }
    
}
