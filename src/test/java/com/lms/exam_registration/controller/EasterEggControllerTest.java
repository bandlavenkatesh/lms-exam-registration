package com.lms.exam_registration.controller;

import com.lms.exam_registration.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EasterEggControllerTest {

    @InjectMocks
    private EasterEggController easterEggController;

    @Mock
    private RestTemplate restTemplate;

    public EasterEggControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNumberFact() {
        when(restTemplate.getForObject("http://numbersapi.com/5", String.class))
                .thenReturn("5 is the number of senses humans have.");

        String fact = easterEggController.getNumberFact(5);
        assertEquals("5 is the number of senses humans have.", fact);
    }

   @Test
    void testGetNumberFact_Failure() {
        // Mock RestTemplate to throw RestClientException
        when(restTemplate.getForObject("http://numbersapi.com/5", String.class))
                .thenThrow(new RestClientException("Service unavailable"));

        // Assert that BadRequestException is thrown
        BadRequestException exception = assertThrows(BadRequestException.class, () -> 
            easterEggController.getNumberFact(5)
        );

        // Assert exception message
        assertEquals("Unable to retrieve fact for the number: 5", exception.getMessage());
    }

}
