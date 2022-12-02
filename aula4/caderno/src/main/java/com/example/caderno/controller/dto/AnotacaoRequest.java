package com.example.caderno.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnotacaoRequest {
    @NotNull
    private String titulo;
    @NotNull
    private String texto;
    @NotNull
    private Long idMateria;
}