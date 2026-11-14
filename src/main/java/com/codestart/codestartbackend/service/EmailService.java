package com.codestart.codestartbackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String centreEmail;
    private final String fromEmail;

    public EmailService(JavaMailSender mailSender,
                        @Value("${app.centre-email}") String centreEmail,
                        @Value("${MAIL_USERNAME}") String fromEmail) {
        this.mailSender = mailSender;
        this.centreEmail = centreEmail;
        this.fromEmail = fromEmail;
    }

    public void sendToStudent(String to, Long referenceId, String studentName) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromEmail);
        mail.setTo(to);
        mail.setSubject("We received your enquiry (Ref " + referenceId + ")");
        mail.setText(
                "Dear " + studentName + ",\n\n"
                + "Thank you for your enquiry at CodeStart Academy.\n"
                + "Your reference number is " + referenceId + ".\n\n"
                + "Our team will contact you soon.\n\n"
                + "CodeStart Academy\n"
                + "+91 70754 05512");
        mailSender.send(mail);
    }

    public void sendToCentre(Long referenceId, String studentName, String course,
                             String phone, String email, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromEmail);
        mail.setTo(centreEmail);
        mail.setSubject("New enquiry (Ref " + referenceId + "): " + course);
        mail.setText(
                "A new enquiry has been received.\n\n"
                + "Reference: " + referenceId + "\n"
                + "Name: " + studentName + "\n"
                + "Course: " + course + "\n"
                + "Phone: " + phone + "\n"
                + "Email: " + email + "\n"
                + "Message: " + (message == null || message.isBlank() ? "-" : message));
        mailSender.send(mail);
    }
    public void sendAdmissionConfirmation(String to, String admissionNumber, String studentName, String course, String batch) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromEmail);
        mail.setTo(to);
        mail.setSubject("Admission application received (" + admissionNumber + ")");
        mail.setText(
                "Dear " + studentName + ",\n\n"
                        + "Thank you for applying to CodeStart Academy.\n"
                        + "Your admission number is " + admissionNumber + ".\n\n"
                        + "Course: " + course + "\n"
                        + "Batch: " + batch + "\n\n"
                        + "Our team will contact you with the next steps.\n\n"
                        + "CodeStart Academy\n"
                        + "+91 70754 05512");
        mailSender.send(mail);
    }
}