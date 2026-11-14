package com.codestart.codestartbackend.service;

import com.codestart.codestartbackend.dto.EnquiryRequest;
import com.codestart.codestartbackend.entity.Enquiry;
import com.codestart.codestartbackend.repository.EnquiryRepository;
import org.springframework.stereotype.Service;

@Service
public class EnquiryService {
    private final EnquiryRepository enquiryRepository;
    private final EmailService emailService;
    public EnquiryService(EnquiryRepository enquiryRepository , EmailService emailService) {
        this.enquiryRepository = enquiryRepository;
        this.emailService = emailService;
    }
    public Long save(EnquiryRequest request){
        Enquiry enquiry = new Enquiry();
        enquiry.setName(request.name());
        enquiry.setEmail(request.email());
        enquiry.setPhone(request.phone());
        enquiry.setMessage(request.message());
        enquiry.setCourse(request.course());
        Enquiry saved = enquiryRepository.save(enquiry);
        Long referenceId =  saved.getId();

        //Sending mails after mail is saved
        //if the email fails the enquiry is still kept
        try{
            emailService.sendToStudent(saved.getEmail(), referenceId , saved.getName());
            emailService.sendToCentre(referenceId , saved.getName(),saved.getCourse() , saved.getPhone(),saved.getEmail() , saved.getMessage());
        } catch (Exception e){
            System.err.println("Email sending failed for enquiry "+referenceId);
        }
        return  referenceId;
    }
}
