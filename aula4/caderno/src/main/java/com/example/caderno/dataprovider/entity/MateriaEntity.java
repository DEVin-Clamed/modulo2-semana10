package com.example.caderno.dataprovider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="materia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn
//    List<AnotacaoEntity> anotacaoEntityList;

    public MateriaEntity(String nome) {
        this.nome = nome;
    }
}
