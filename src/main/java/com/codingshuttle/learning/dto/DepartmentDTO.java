package com.codingshuttle.learning.dto;

import com.codingshuttle.learning.annotations.PasswordStrengthValidation;
import com.codingshuttle.learning.annotations.PrimeOrNotValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department Title cannot be null")
    @Size(min = 2, max = 20, message = "Department Title should be in range: [2, 20]")
    private String title;

    @AssertTrue(message = "Department must be active")
    private Boolean isActive;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime updatedAt;

    @PrimeOrNotValidation
    @PositiveOrZero
    private Integer employeeCount;

    @PasswordStrengthValidation
    @NotBlank(message = "Password is required")
    private String password;

}
