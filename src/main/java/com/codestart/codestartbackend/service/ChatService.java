package com.codestart.codestartbackend.service;

import com.codestart.codestartbackend.entity.Faq;
import com.codestart.codestartbackend.repository.FaqRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class ChatService {

    private static final String FALLBACK =
            "I'm not sure about that. Please call us on +91 70754 05512 or email "
            + "codestart9989@gmail.com, and our team will help you.";

    private final FaqRepository faqRepository;

    public ChatService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public String reply(String message) {
        if (message == null || message.isBlank()) {
            return "Please type your question, and I'll try to help.";
        }

        String text = message.toLowerCase(Locale.ROOT);
        List<Faq> faqs = faqRepository.findAll();

        for (Faq faq : faqs) {
            for (String keyword : faq.getKeywords().split(",")) {
                String k = keyword.trim().toLowerCase(Locale.ROOT);
                if (!k.isEmpty() && text.contains(k)) {
                    return faq.getAnswer();
                }
            }
        }
        return FALLBACK;
    }
}