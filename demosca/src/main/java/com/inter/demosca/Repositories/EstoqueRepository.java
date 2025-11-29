package com.inter.demosca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inter.demosca.Entities.EstoqueEntity;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Integer>{

}
