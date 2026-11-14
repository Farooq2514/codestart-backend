package com.codestart.codestartbackend.controller;

import com.codestart.codestartbackend.entity.AdmissionDocument;
import com.codestart.codestartbackend.entity.DocumentType;
import com.codestart.codestartbackend.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/admissions/{admissionId}/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> upload(
            @PathVariable Long admissionId,
            @RequestParam DocumentType type,
            @RequestParam MultipartFile file) throws IOException {

        AdmissionDocument document = documentService.upload(admissionId, type, file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "documentId", document.getId(),
                        "type", document.getDocumentType().name(),
                        "message", "Document uploaded"));
    }
}