package com.codestart.codestartbackend.dto;

import com.codestart.codestartbackend.entity.AdmissionBatch;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AdmissionRequest(
        @NotBlank @Size(max = 120) String studentName,
        @NotNull @Past LocalDate dateOfBirth,
        @NotBlank @Email @Size(max = 120) String email,
        @NotBlank @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits") String phone,
        @NotBlank @Size(max = 120) String parentName,
        @NotBlank @Size(max = 60) String course,
        @NotBlank @Size(max = 120) String qualification,
        @NotNull AdmissionBatch batch
) {
}
