package com.codestart.codestartbackend.controller;

import com.codestart.codestartbackend.entity.Admission;
import com.codestart.codestartbackend.entity.AdmissionStatus;
import com.codestart.codestartbackend.repository.AdmissionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/admissions")
public class AdminAdmissionController {

    private final AdmissionRepository admissionRepository;

    public AdminAdmissionController(AdmissionRepository admissionRepository) {
        this.admissionRepository = admissionRepository;
    }

    @GetMapping
    public List<Admission> list() {
        return admissionRepository.findAllByOrderByCreatedAtDesc();
    }

    @PutMapping("/{id}/status")
    public Admission updateStatus(@PathVariable Long id,
                                  @RequestParam AdmissionStatus status) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admission not found: " + id));
        admission.setStatus(status);
        return admissionRepository.save(admission);
    }

    @GetMapping("/export")
    public ResponseEntity<String> export() {
        StringBuilder csv = new StringBuilder();
        csv.append("id,admissionNumber,studentName,dateOfBirth,email,phone,parentName,course,qualification,batch,status,createdAt\n");
        for (Admission a : admissionRepository.findAllByOrderByCreatedAtDesc()) {
            csv.append(csvLine(
                    String.valueOf(a.getId()),
                    a.getAdmissionNumber(),
                    a.getStudentName(),
                    String.valueOf(a.getDateOfBirth()),
                    a.getEmail(),
                    a.getPhone(),
                    a.getParentName(),
                    a.getCourse(),
                    a.getQualification(),
                    a.getAdmissionBatch().name(),
                    a.getStatus().name(),
                    String.valueOf(a.getCreatedAt())));
        }
        return AdminEnquiryController.csvResponse(csv.toString(), "admissions.csv");
    }

    private String csvLine(String... values) {
        return AdminEnquiryController.csvLine(values);
    }
}