package com.inter.demosca.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioMovimentacaoDTO {

    private int id_movimentacao;
    private int quantidade;
    private String nome_material;
    private String usuario;
    private String tipo_movimentacao;
    } 