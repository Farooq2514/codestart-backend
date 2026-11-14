package com.codestart.codestartbackend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_document")
public class AdmissionDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admission_id", nullable = false)
    private Admission admission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private DocumentType documentType;

    @Column(nullable = false , length = 255)
    private String originalFileName;

    @Column(nullable = false , length = 255)
    private String storeFileName;

    @Column(nullable = false )
    private long sizeBytes;

    @Column(nullable = false , length = 100)
    private String contentType;

    @Column(nullable = false , updatable = false)
    private LocalDateTime uploadedAt;


    @PrePersist
    void onCreate() {
        this.uploadedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoreFileName() {
        return storeFileName;
    }

    public void setStoreFileName(String storeFileName) {
        this.storeFileName = storeFileName;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
