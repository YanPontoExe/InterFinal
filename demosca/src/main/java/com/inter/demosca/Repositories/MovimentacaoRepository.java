package com.inter.demosca.Repositories;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.inter.demosca.Entities.MovimentacaoEntity;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Integer>{

    // Certifique-se de que o nome da function está correto e que o parâmetro é passado.
    @Query(value = "SELECT * FROM fn_relatorioMovimentacaoMaterial(:idMaterial)",
           nativeQuery = true)
    List<Object[]> listarMovimentacoes(@Param("idMaterial") Integer idMaterial);

}  