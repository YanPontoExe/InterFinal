package com.inter.demosca.Entities;

import java.time.LocalDateTime;

//import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_estoque")
public class EstoqueEntity {
    @Id
    private int id; // mesma PK do Material

    @OneToOne
    @MapsId            // <- faz o id ser FK para Material!
    @JoinColumn( name="id_material", nullable = false) //chave estrangeira do usuario
    private int cod_material;
    private String qtd_atual;
    private String qtd_min;
    private int qtd_max;
}
