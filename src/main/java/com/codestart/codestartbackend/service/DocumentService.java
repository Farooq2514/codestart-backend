package com.codestart.codestartbackend.service;

import com.codestart.codestartbackend.entity.Admission;
import com.codestart.codestartbackend.entity.AdmissionDocument;
import com.codestart.codestartbackend.entity.DocumentType;
import com.codestart.codestartbackend.repository.AdmissionDocumentRepository;
import com.codestart.codestartbackend.repository.AdmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentService {

    private final AdmissionRepository admissionRepository;
    private final AdmissionDocumentRepository documentRepository;
    private final FileStorageService fileStorageService;

    public DocumentService(AdmissionRepository admissionRepository,
                           AdmissionDocumentRepository documentRepository,
                           FileStorageService fileStorageService) {
        this.admissionRepository = admissionRepository;
        this.documentRepository = documentRepository;
        this.fileStorageService = fileStorageService;
    }

    public AdmissionDocument upload(Long admissionId, DocumentType type, MultipartFile file) throws IOException {
        Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new IllegalArgumentException("Admission not found: " + admissionId));

        String storedName = fileStorageService.store(file);

        AdmissionDocument document = new AdmissionDocument();
        document.setAdmission(admission);
        document.setDocumentType(type);
        document.setOriginalFileName(file.getOriginalFilename());
        document.setStoreFileName(storedName);
        document.setContentType(file.getContentType());
        document.setSizeBytes(file.getSize());

        return documentRepository.save(document);
    }
}