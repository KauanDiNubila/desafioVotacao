package com.example.api.rest.sem.web01.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociadoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;
}
