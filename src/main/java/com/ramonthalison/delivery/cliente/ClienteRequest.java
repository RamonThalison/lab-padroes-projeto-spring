package com.ramonthalison.delivery.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Record usado como DTO para impedir que a entidade receba diretamente o JSON da API.
public record ClienteRequest(
    @NotBlank @Size(max = 100) String nome,
    @NotBlank @Pattern(regexp = "\\d{8}", message = "deve conter exatamente 8 números") String cep
) {
}
