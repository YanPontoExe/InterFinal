package com.inter.demosca.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_saida")
public class SaidaEntity extends MovimentacaoEntity {

    @Column(nullable = false)
    private String cod_funcionario;
    @Column(length = 100, nullable = false)
    private String motivo; //venda, transferência, devolução, etc.
    @Column(nullable = false)
    private LocalDateTime data_saida;
}
