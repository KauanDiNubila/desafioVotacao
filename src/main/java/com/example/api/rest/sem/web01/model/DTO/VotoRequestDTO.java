package com.example.api.rest.sem.web01.model.DTO;

import com.example.api.rest.sem.web01.model.enumeration.TipoVoto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequestDTO {

    @NotNull
    private Long associadoId;

    @NotNull
    private TipoVoto voto;
}
