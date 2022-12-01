package com.example.caderno.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnotacaoResponse {
    private String titulo;
    private String texto;
    private MateriaResponse materiaResponse;
}
