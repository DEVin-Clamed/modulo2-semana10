package com.example.caderno.dataprovider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="nota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnotacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String texto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn
    private MateriaEntity materiaEntity; // uma materia para várias anotações
    //cada anotação de uma matéria tem essa materia dentro dela

    public AnotacaoEntity(String titulo, String nota, MateriaEntity materiaEntity) {
        this.titulo = titulo;
        this.texto = nota;
        this.dataCriacao = LocalDateTime.now();
        this.materiaEntity = materiaEntity;
    }
}
