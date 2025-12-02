package com.inter.demosca.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inter.demosca.Controllers.MovimentacaoController;
import com.inter.demosca.Entities.MovimentacaoEntity;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Integer>{

    // Chamada da procedure no sql
    @Procedure(name = "sp_relatorioMovimentacaoMaterial")
    List<Object[]> gerarRelatorio(
        @Param("idMaterial") Integer idMaterial
    );
} 