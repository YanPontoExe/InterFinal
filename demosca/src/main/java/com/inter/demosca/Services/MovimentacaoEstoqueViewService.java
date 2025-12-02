package com.inter.demosca.Services;

import com.inter.demosca.Entities.MovimentacaoEstoqueView;
import com.inter.demosca.Repositories.MovimentacaoEstoqueViewRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovimentacaoEstoqueViewService {

    private final MovimentacaoEstoqueViewRepository repository;

    public MovimentacaoEstoqueViewService(MovimentacaoEstoqueViewRepository repository) {
        this.repository = repository;
    }

    public List<MovimentacaoEstoqueView> listarTodos() {
        return repository.findAll();
    }

    public MovimentacaoEstoqueView buscarPorMaterial(String material) {
        return repository.findById(material).orElse(null);
    }
}
