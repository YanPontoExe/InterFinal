package com.inter.demosca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.inter.demosca.Entities.EntradaEntity;

@Repository
public interface EntradaRepository extends JpaRepository<EntradaEntity, Integer>{

    // Mapeia a function SQL de Listar todas as Entradas
    @Query(value = "SELECT * FROM fn_listarEntradas()", nativeQuery = true)
    List<EntradaEntity> listarEntradas();

}
