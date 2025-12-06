package com.inter.demosca.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_funcionario")
@Inheritance(strategy = InheritanceType.JOINED)
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_funcionario;
    @Column(length = 50, nullable = false)
    private String nome_funcionario; 
    @Column(length = 30, nullable = false)
    private String setor;
    @Column(length = 10, nullable = false)
    private String turno;
    private LocalDateTime data_contratacao;
    private int status; //1 Ativo ou 2 Inativo
}