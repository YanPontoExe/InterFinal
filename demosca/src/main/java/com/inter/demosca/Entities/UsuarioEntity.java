package com.inter.demosca.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioEntity extends FuncionarioEntity {
    @Column(nullable = false)
    private int cod_funcionario;
    @Column(length = 15, nullable = false)
    private String usuario;
    @Column(length = 12, nullable = false)
    private String senha;
    private boolean status; //true Ativo ou false Inativo
    private int tipo_usuario; //1 usuario comum ou 2 usuario adm
}