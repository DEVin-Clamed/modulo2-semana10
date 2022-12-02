package com.example.caderno.dataprovider.repository;

import com.example.caderno.dataprovider.entity.AnotacaoEntity;
import com.example.caderno.dataprovider.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {




}
