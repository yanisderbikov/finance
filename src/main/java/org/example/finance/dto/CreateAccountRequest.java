package org.example.finance.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAccountRequest {
    @NotBlank(message = "Account name is required")
    private String name;
}

