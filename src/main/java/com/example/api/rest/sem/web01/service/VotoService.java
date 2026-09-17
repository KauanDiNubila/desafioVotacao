package com.example.api.rest.sem.web01.service;

import com.example.api.rest.sem.web01.exception.RecursoNaoEncontradoException;
import com.example.api.rest.sem.web01.exception.RegraNegocioException;
import com.example.api.rest.sem.web01.model.DTO.ResultadoResponseDTO;
import com.example.api.rest.sem.web01.model.entity.Associado;
import com.example.api.rest.sem.web01.model.entity.SessaoVotacao;
import com.example.api.rest.sem.web01.model.entity.Voto;
import com.example.api.rest.sem.web01.model.enumeration.TipoVoto;
import com.example.api.rest.sem.web01.model.repository.AssociadoRepository;
import com.example.api.rest.sem.web01.model.repository.SessaoVotacaoRepository;
import com.example.api.rest.sem.web01.model.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final AssociadoRepository associadoRepository;

    public void votar(Long pautaId, Long associadoId, TipoVoto tipo) {

        SessaoVotacao sessao = sessaoVotacaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Nenhuma sessão de votação encontrada para esta pauta"));

        if (!Boolean.TRUE.equals(sessao.getAtiva()) || LocalDateTime.now().isAfter(sessao.getDataFim())) {
            throw new RegraNegocioException("Sessão de votação encerrada");
        }

        Associado associado = associadoRepository.findById(associadoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Associado não encontrado"));

        if (votoRepository.existsBySessaoVotacaoAndAssociado_Id(sessao, associadoId)) {
            throw new RegraNegocioException("Associado já votou nesta sessão");
        }

        Voto voto = new Voto();
        voto.setSessaoVotacao(sessao);
        voto.setAssociado(associado);
        voto.setVoto(tipo);
        voto.setDataVoto(LocalDateTime.now());

        votoRepository.save(voto);
    }

    public ResultadoResponseDTO obterResultado(Long pautaId) {

        SessaoVotacao sessao = sessaoVotacaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Nenhuma sessão de votação encontrada para esta pauta"));

        long votosSim = votoRepository.countBySessaoVotacaoAndVoto(sessao, TipoVoto.SIM);
        long votosNao = votoRepository.countBySessaoVotacaoAndVoto(sessao, TipoVoto.NAO);

        String resultado;
        if (votosSim > votosNao) {
            resultado = "APROVADA";
        } else if (votosNao > votosSim) {
            resultado = "REJEITADA";
        } else {
            resultado = "EMPATE";
        }

        return new ResultadoResponseDTO(pautaId, votosSim, votosNao, resultado);
    }
}
