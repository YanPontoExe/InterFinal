package com.inter.demosca.Entities;

import java.time.LocalDateTime;

//import jakarta.annotation.Generated;
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
@Table(name = "vw_movimentacao_estoque")
public class MovimentacaoEstoqueView {

    @Id
    @Column(name = "material") // Aqui precisa ser algo único; se material não for único, usar @IdClass
    private String material;

    @Column(name = "marca")
    private String marca;

    @Column(name = "data_cadastro_material")
    private LocalDateTime dataCadastroMaterial;

    @Column(name = "status")
    private int statusMaterial;

    @Column(name = "nome_marca")
    private String nomeMarca;

    @Column(name = "status_marca")
    private boolean statusMarca;

    @Column(name = "pais_origem")
    private String paisOrigemMarca;

    @Column(name = "descricao_marca")
    private String descricaoMarca;

    @Column(name = "data_cadastro_marca")
    private LocalDateTime dataCadastroMarca;

    @Column(name = "nome_fornecedor")
    private String nomeFornecedor;

    @Column(name = "cnpj")
    private String cnpj;
}
