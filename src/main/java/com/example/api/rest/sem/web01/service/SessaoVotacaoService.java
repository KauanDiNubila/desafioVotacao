package com.example.api.rest.sem.web01.service;

import com.example.api.rest.sem.web01.exception.RecursoNaoEncontradoException;
import com.example.api.rest.sem.web01.exception.RegraNegocioException;
import com.example.api.rest.sem.web01.model.entity.Pauta;
import com.example.api.rest.sem.web01.model.entity.SessaoVotacao;
import com.example.api.rest.sem.web01.model.repository.PautaRepository;
import com.example.api.rest.sem.web01.model.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoVotacao abrirSessao(Long pautaId, Integer duracaoMinutos) {

        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pauta não encontrada"));

        sessaoVotacaoRepository.findByPautaId(pautaId)
                .filter(this::estaAberta)
                .ifPresent(sessao -> {
                    throw new RegraNegocioException("Já existe uma sessão de votação aberta para esta pauta");
                });

        if (duracaoMinutos == null) {
            duracaoMinutos = 1;
        }

        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setPauta(pauta);
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusMinutes(duracaoMinutos));
        sessao.setAtiva(true);

        return sessaoVotacaoRepository.save(sessao);
    }

    private boolean estaAberta(SessaoVotacao sessao) {
        return Boolean.TRUE.equals(sessao.getAtiva())
                && LocalDateTime.now().isBefore(sessao.getDataFim());
    }
}
