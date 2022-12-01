package com.example.caderno.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnotacaoRequest {
    private String titulo;
    private String texto;
    private Long idMateria;
}