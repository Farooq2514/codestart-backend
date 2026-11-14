package com.codestart.codestartbackend.repository;

import com.codestart.codestartbackend.entity.AdmissionDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionDocumentRepository extends JpaRepository<AdmissionDocument , Long> {

    List<AdmissionDocument> findByAdmissionId(Long admissionId);

}
