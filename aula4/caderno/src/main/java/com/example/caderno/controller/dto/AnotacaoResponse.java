package com.example.caderno.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnotacaoResponse {
    private Long id;
    private String titulo;
    private String texto;
//    private String materia;
    private MateriaResponse materiaResponse;
}
