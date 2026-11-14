package com.codestart.codestartbackend.repository;

import com.codestart.codestartbackend.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}