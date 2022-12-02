package com.example.caderno.dataprovider.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tag")
@Getter
@Setter
@NoArgsConstructor // toda entity tem que ter um construtor sem argumentos/parametros -> requisito do JPA
@AllArgsConstructor
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_id")
    private AnotacaoEntity notaEntity;

    public TagEntity(String tag, AnotacaoEntity notaEntity) { //construtor customizado, pois não precisamos gerar o ID
        this.tag = tag;
        this.notaEntity = notaEntity;
    }
}
