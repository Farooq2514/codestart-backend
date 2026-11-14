package com.codestart.codestartbackend.controller;

import com.codestart.codestartbackend.dto.EnquiryRequest;
import com.codestart.codestartbackend.service.EnquiryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {
    private final EnquiryService enquiryService;
    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }
    @PostMapping
    public ResponseEntity<Map<String , Object>>create(@Valid@RequestBody EnquiryRequest request){
        Long referenceId = enquiryService.save(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("referenceId", referenceId , "message" , "Enquiry Received"));
    }
}
