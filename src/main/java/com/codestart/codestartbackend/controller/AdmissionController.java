package com.codestart.codestartbackend.controller;

import com.codestart.codestartbackend.dto.AdmissionRequest;
import com.codestart.codestartbackend.entity.Admission;
import com.codestart.codestartbackend.service.AdmissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {
    private final AdmissionService admissionService;
    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @PostMapping
    public ResponseEntity<Map<String , Object>> create(@Valid @RequestBody AdmissionRequest request){
        Admission admission = new Admission();
        admission.setStudentName(request.studentName());
        admission.setDateOfBirth(request.dateOfBirth());
        admission.setEmail(request.email());
        admission.setPhone(request.phone());
        admission.setParentName(request.parentName());
        admission.setCourse(request.course());
        admission.setQualification(request.qualification());
        admission.setAdmissionBatch(request.batch());

        Admission saved = admissionService.save(admission);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("admissionNumber" , saved.getAdmissionNumber(),
               "message" ,"Admission Application Received"));
    }
}
