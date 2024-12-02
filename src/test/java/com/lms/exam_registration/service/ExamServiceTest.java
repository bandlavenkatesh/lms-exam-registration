package com.lms.exam_registration.service;

import com.lms.exam_registration.entity.Exam;
import com.lms.exam_registration.entity.Student;
import com.lms.exam_registration.entity.Subject;
import com.lms.exam_registration.exception.BadRequestException;
import com.lms.exam_registration.repository.ExamRepository;
import com.lms.exam_registration.repository.StudentRepository;
import com.lms.exam_registration.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamServiceTest {

    @InjectMocks
    private ExamService examService;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private StudentRepository studentRepository;

    public ExamServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterStudentForExam_Valid() {
        Student student = new Student();
        Subject subject = new Subject();
        Exam exam = new Exam();

        subject.setId(2L);
        student.getEnrolledSubjects().add(subject);
        exam.setSubject(subject);

        when(examRepository.findById(3L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        String result = examService.registerStudentForExam(3L, 1L);

        assertEquals("Student registered for the exam successfully!", result);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testRegisterStudentForExam_NotEnrolledSubject() {
        Student student = new Student();
        Exam exam = new Exam();
        exam.setSubject(new Subject());

        when(examRepository.findById(3L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertThrows(BadRequestException.class, () -> examService.registerStudentForExam(3L, 1L));
    }
}
