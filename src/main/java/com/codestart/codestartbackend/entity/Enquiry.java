package com.codestart.codestartbackend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "enquiries")
public class Enquiry {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
@Column(nullable = false , length = 120)
  private String name;
    @Column(nullable = false, length = 120)
    private String email;
    @Column(nullable = false, length = 15)
    private String phone;
    @Column(nullable = false, length = 60)
    private String course;
    @Column(length = 1000)
    private String message;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private EnquiryStatus status = EnquiryStatus.NEW;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public EnquiryStatus getStatus() {
        return status;
    }

    public void setStatus(EnquiryStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
public Long getId() {
	return id;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
