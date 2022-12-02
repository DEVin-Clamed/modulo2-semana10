package com.example.caderno.dataprovider.repository;

import com.example.caderno.dataprovider.entity.AnotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnotacaoRepository extends JpaRepository<AnotacaoEntity, Long> {
    //    @Query("SELECT a FROM AnotacaoEntity a WHERE a.materiaEntity.id = :id") //JPQL
    public List<AnotacaoEntity> findAnotacaoEntitiesByMateriaEntity_Id(Long id); //segue o padrão de query do JPA

    @Query("SELECT a FROM AnotacaoEntity a WHERE a.materiaEntity.nome = :nome") //JPQL
    public List<AnotacaoEntity> findAllByMateriaNome(@Param("nome") String nome);
    // não segue o padrão de nome do JPA, precisa da @Query
}
