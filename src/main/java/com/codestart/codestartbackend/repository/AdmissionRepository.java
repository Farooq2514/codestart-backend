package com.codestart.codestartbackend.repository;

import com.codestart.codestartbackend.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    Optional<Admission> findByAdmissionNumber(String admissionNumber);
    List<Admission> findAllByOrderByCreatedAtDesc();
}