package com.inter.demosca.Repositories;

import com.inter.demosca.Entities.MovimentacaoEstoqueView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoEstoqueViewRepository extends JpaRepository<MovimentacaoEstoqueView, String> {
    // Consultas customizadas se necessário
}
