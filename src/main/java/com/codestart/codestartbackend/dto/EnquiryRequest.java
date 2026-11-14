package com.codestart.codestartbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnquiryRequest(
       @NotBlank @Size(max = 120) String name ,
       @NotBlank @Email @Size(max = 120) String email ,
       @NotBlank @Pattern(regexp = "^[0-9]{10}$" , message = "Phone must be 10 digits")String phone,
       @NotBlank @Size(max = 60)String course,
       @Size(max = 1000)String message
) { }
