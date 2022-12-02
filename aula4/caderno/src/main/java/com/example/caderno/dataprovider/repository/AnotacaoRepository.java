package com.example.caderno.dataprovider.repository;

import com.example.caderno.dataprovider.entity.AnotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnotacaoRepository extends JpaRepository<AnotacaoEntity, Long> {
//    @Query("SELECT a FROM AnotacaoEntity a WHERE a.materiaEntity.id = :id") //JPQL
    public List<AnotacaoEntity> findAnotacaoEntitiesByMateriaEntity_Id(Long id);
}
