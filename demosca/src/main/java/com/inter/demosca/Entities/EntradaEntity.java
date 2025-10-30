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
@Table(name = "tb_entrada")
public class EntradaEntity extends MovimentacaoEntity {

    @Column(nullable = false)
    private int cod_fornecedor;
    @Column(length = 44, nullable = false)
    private String notaFiscal;
    @Column(nullable = false)
    private LocalDateTime dataEntrada;

}
