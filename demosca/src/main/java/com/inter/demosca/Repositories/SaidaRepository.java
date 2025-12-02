package com.inter.demosca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.inter.demosca.Entities.SaidaEntity;

@Repository
public interface SaidaRepository extends JpaRepository<SaidaEntity, Integer> {
    
    // Mapeia a function SQL de Listar todas as Saidas
    @Query(value = "SELECT * FROM fn_listarSaidas()", nativeQuery = true)
    List<SaidaEntity> listarSaidas();
    
}
