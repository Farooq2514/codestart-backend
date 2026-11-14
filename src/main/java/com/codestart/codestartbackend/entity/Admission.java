package com.codestart.codestartbackend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "admissions")
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true , length = 30)
    private String admissionNumber;

    @Column(nullable = false , length = 120)
    private String studentName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false ,  length = 120)
    private String email;

    @Column(nullable = false , length = 15)
    private String phone;

    @Column(nullable = false , length = 120)
    private String parentName;

    @Column(nullable = false , length = 60)
    private String course;

    @Column(nullable = false , length = 120)
    private String qualification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private AdmissionStatus status = AdmissionStatus.SUBMITTED;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private AdmissionBatch admissionBatch;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt;
@PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public AdmissionStatus getStatus() {
        return status;
    }

    public void setStatus(AdmissionStatus status) {
        this.status = status;
    }

    public AdmissionBatch getAdmissionBatch() {
        return admissionBatch;
    }

    public void setAdmissionBatch(AdmissionBatch admissionBatch) {
        this.admissionBatch = admissionBatch;
    }
}
