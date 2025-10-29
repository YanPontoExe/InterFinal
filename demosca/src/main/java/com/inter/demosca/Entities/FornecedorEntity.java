package com.inter.demosca.Entities;

import java.time.LocalDateTime;

//import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_fornecedor")
public class FornecedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_fornecedor;
    @Column(length = 50, nullable = false)
    private String nome_fornecedor;
    @Column(length = 50, nullable = false)
    private String material_fornecido;
    @Column(nullable = false)
    private int valor;
    @Column(nullable = false)
    private int cod_fornecedor;
    @Column(length = 100, nullable = false)
    private String descricao;
    @Column(length = 50, nullable = false)
    private String marca;
}
