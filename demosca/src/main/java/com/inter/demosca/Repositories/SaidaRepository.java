package com.inter.demosca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inter.demosca.Entities.SaidaEntity;

@Repository
public interface SaidaRepository extends JpaRepository<SaidaEntity, Integer> {

}
