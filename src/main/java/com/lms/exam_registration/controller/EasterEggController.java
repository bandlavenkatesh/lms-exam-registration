package com.lms.exam_registration.controller;

import com.lms.exam_registration.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hidden-feature")
public class EasterEggController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{number}")
    public String getNumberFact(@PathVariable int number) {
        try {
            String url = "http://numbersapi.com/" + number;
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException ex) {
            throw new BadRequestException("Unable to retrieve fact for the number: " + number);
        }
    }
}
