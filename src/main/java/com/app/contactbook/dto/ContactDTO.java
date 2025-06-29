package com.app.contactbook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.antlr.v4.runtime.misc.NotNull;

public record ContactDTO(

        @NotBlank(message = "Name cannot be blank!")
        String name,

        @Email(message = "Invalid email address!")
        String email,

        @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
        String phoneNumber
) {

}
