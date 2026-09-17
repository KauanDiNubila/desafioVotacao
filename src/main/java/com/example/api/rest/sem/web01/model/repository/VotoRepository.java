package com.example.api.rest.sem.web01.model.repository;

import com.example.api.rest.sem.web01.model.entity.SessaoVotacao;
import com.example.api.rest.sem.web01.model.entity.Voto;
import com.example.api.rest.sem.web01.model.enumeration.TipoVoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsBySessaoVotacaoAndAssociado_Id(
            SessaoVotacao sessaoVotacao,
            Long associadoId
    );

    long countBySessaoVotacaoAndVoto(
            SessaoVotacao sessaoVotacao,
            TipoVoto voto
    );
}
