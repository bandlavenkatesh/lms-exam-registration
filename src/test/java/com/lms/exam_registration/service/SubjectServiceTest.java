package com.lms.exam_registration.service;

import com.lms.exam_registration.entity.Subject;
import com.lms.exam_registration.exception.ResourceNotFoundException;
import com.lms.exam_registration.repository.SubjectRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    public SubjectServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSubject() {
        Subject subject = new Subject();
        subject.setName("Math");
        when(subjectRepository.save(subject)).thenReturn(subject);

        Subject savedSubject = subjectService.addSubject(subject);
        assertNotNull(savedSubject);
        assertEquals("Math", savedSubject.getName());
    }

    @Test
    void testGetSubjectById_ValidId() {
        Subject subject = new Subject();
        subject.setId(1L);
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Subject foundSubject = subjectService.getSubjectById(1L);
        assertNotNull(foundSubject);
        assertEquals(1L, foundSubject.getId());
    }

    @Test
    void testGetSubjectById_InvalidId() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> subjectService.getSubjectById(1L));
    }
}
