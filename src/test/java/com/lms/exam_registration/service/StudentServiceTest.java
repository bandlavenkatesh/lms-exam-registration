package com.lms.exam_registration.service;

import com.lms.exam_registration.entity.Student;
import com.lms.exam_registration.entity.Subject;
import com.lms.exam_registration.exception.ResourceNotFoundException;
import com.lms.exam_registration.repository.StudentRepository;
import com.lms.exam_registration.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    public StudentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnrollStudentInSubject() {
        Student student = new Student();
        student.setId(1L);

        Subject subject = new Subject();
        subject.setId(2L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(2L)).thenReturn(Optional.of(subject));

        String result = studentService.enrollStudentInSubject(1L, 2L);

        assertEquals("Student enrolled in the subject successfully!", result);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testEnrollStudentInSubject_InvalidStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.enrollStudentInSubject(1L, 2L));
    }
}
