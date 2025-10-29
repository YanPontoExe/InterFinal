package com.inter.demosca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inter.demosca.Entities.EntradaEntity;
import com.inter.demosca.Entities.UsuarioEntity;

@Repository
public interface EntradaRepository extends JpaRepository<EntradaEntity, Integer>{

}
