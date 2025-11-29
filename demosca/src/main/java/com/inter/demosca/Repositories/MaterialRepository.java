package com.inter.demosca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inter.demosca.Entities.MaterialEntity;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Integer>{

     @Query(value = "SELECT dbo.fn_totalMovimentacoesMaterial(:idMaterial)", nativeQuery = true)
    Integer buscarTotalMovimentacoes(@Param("idMaterial") Integer idMaterial);

}