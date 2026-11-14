package com.codestart.codestartbackend;

import com.codestart.codestartbackend.dto.EnquiryRequest;
import com.codestart.codestartbackend.entity.Enquiry;
import com.codestart.codestartbackend.repository.EnquiryRepository;
import com.codestart.codestartbackend.service.EmailService;
import com.codestart.codestartbackend.service.EnquiryService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EnquiryServiceTest {

    @Test
    void savesEnquiryAndReturnsReferenceId() {
        EnquiryRepository repository = mock(EnquiryRepository.class);
        EmailService emailService = mock(EmailService.class);

        Enquiry saved = new Enquiry();
        saved.setName("Asha Rao");
        saved.setEmail("asha@example.com");
        saved.setPhone("9876543210");
        saved.setCourse("Java");
        when(repository.save(any(Enquiry.class))).thenReturn(saved);

        EnquiryService service = new EnquiryService(repository, emailService);
        EnquiryRequest request = new EnquiryRequest(
                "Asha Rao", "asha@example.com", "9876543210", "Java", "Batch timings?");

        service.save(request);

        ArgumentCaptor<Enquiry> captor = ArgumentCaptor.forClass(Enquiry.class);
        verify(repository).save(captor.capture());
        assertEquals("Asha Rao", captor.getValue().getName());
        assertEquals("Java", captor.getValue().getCourse());
        verify(emailService).sendToStudent(any(), any(), any());
        verify(emailService).sendToCentre(any(), any(), any(), any(), any(), any());
    }

    @Test
    void stillSavesWhenEmailFails() {
        EnquiryRepository repository = mock(EnquiryRepository.class);
        EmailService emailService = mock(EmailService.class);

        Enquiry saved = new Enquiry();
        saved.setName("Asha Rao");
        saved.setEmail("asha@example.com");
        saved.setPhone("9876543210");
        saved.setCourse("Java");
        when(repository.save(any(Enquiry.class))).thenReturn(saved);
        org.mockito.Mockito.doThrow(new RuntimeException("SMTP down"))
                .when(emailService).sendToStudent(any(), any(), any());

        EnquiryService service = new EnquiryService(repository, emailService);
        EnquiryRequest request = new EnquiryRequest(
                "Asha Rao", "asha@example.com", "9876543210", "Java", null);

        service.save(request);

        verify(repository).save(any(Enquiry.class));
    }
}