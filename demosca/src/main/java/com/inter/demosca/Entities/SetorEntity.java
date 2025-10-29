package com.inter.demosca.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_setor")
public class SetorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_setor;
    @Column(length = 50, nullable = false)
    private String descricao;
    
}