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
@Table(name="tb_mateiral")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_material;
    @Column(length = 50, nullable = false)
    private String descricao;
    @Column(length = 50)
    private String marca;
    @JoinColumn( name="id_fornecedor", nullable = false) //chave estrangeira de fornecedor
    private int cod_fornecedor; 
    @Column(nullable = false)
    private LocalDateTime dataCadastro; //data  de criação do registro do material
    private int status;
}
