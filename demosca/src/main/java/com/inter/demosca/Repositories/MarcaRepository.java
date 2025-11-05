package com.inter.demosca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inter.demosca.Entities.MarcaEntity;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer>{

}
