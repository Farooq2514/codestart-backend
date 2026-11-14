package com.codestart.codestartbackend.controller;

import com.codestart.codestartbackend.entity.Enquiry;
import com.codestart.codestartbackend.entity.EnquiryStatus;
import com.codestart.codestartbackend.repository.EnquiryRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/enquiries")
public class AdminEnquiryController {

    private final EnquiryRepository enquiryRepository;

    public AdminEnquiryController(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    @GetMapping
    public List<Enquiry> list() {
        return enquiryRepository.findAllByOrderByCreatedAtDesc();
    }

    @PutMapping("/{id}/status")
    public Enquiry updateStatus(@PathVariable Long id,
                                @RequestParam EnquiryStatus status) {
        Enquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enquiry not found: " + id));
        enquiry.setStatus(status);
        return enquiryRepository.save(enquiry);
    }

    @GetMapping("/export")
    public ResponseEntity<String> export() {
        StringBuilder csv = new StringBuilder();
        csv.append("id,name,email,phone,course,message,status,createdAt\n");
        for (Enquiry e : enquiryRepository.findAllByOrderByCreatedAtDesc()) {
            csv.append(csvLine(
                    String.valueOf(e.getId()),
                    e.getName(),
                    e.getEmail(),
                    e.getPhone(),
                    e.getCourse(),
                    e.getMessage(),
                    e.getStatus().name(),
                    String.valueOf(e.getCreatedAt())));
        }
        return csvResponse(csv.toString(), "enquiries.csv");
    }

    static String csvLine(String... values) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) line.append(",");
            String v = values[i] == null ? "" : values[i].replace("\"", "\"\"");
            line.append("\"").append(v).append("\"");
        }
        return line.append("\n").toString();
    }

    static ResponseEntity<String> csvResponse(String body, String fileName) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(body);
    }
}