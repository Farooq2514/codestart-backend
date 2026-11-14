package com.codestart.codestartbackend.service;

import com.codestart.codestartbackend.entity.Admission;
import com.codestart.codestartbackend.repository.AdmissionRepository;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class AdmissionService {
    private final AdmissionRepository admissionRepository;
    private final EmailService emailService;
    public AdmissionService(AdmissionRepository admissionRepository , EmailService emailService) {
        this.admissionRepository = admissionRepository;
        this.emailService = emailService;
    }

    public Admission save(Admission admission) {
        Admission saved =  admissionRepository.save(admission);
        saved.setAdmissionNumber(buildAdmissionNumber(saved.getId()));
        Admission finalSaved =  admissionRepository.save(saved);
         try{
             emailService.sendAdmissionConfirmation(
                     finalSaved.getEmail(),
                     finalSaved.getAdmissionNumber(),
                     finalSaved.getStudentName(),
                     finalSaved.getCourse(),
                     finalSaved.getAdmissionNumber()
             );
         }catch(Exception e){
             System.err.println("Admission email failed for "+finalSaved.getAdmissionNumber());
         }
         return finalSaved;
    }
    private String buildAdmissionNumber(Long id) {
        return "ADM-" + Year.now().getValue() + "-" + String.format("%04d", id);
    }
}
