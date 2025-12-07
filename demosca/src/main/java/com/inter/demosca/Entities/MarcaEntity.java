package com.inter.demosca.Entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_marca")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_marca;
    @Column(length = 50, nullable = false)
    private String nome_marca;
    @Column(length = 20, nullable = false)
    private String pais_origem;
    @Column(length = 50, nullable = true)
    private String descricao_marca;
    private LocalDateTime dataCadastro;
    private boolean status; // (1) true = ativo, (0) false = inativo
}
