package com.example.api.rest.sem.web01.service;

import com.example.api.rest.sem.web01.exception.RecursoNaoEncontradoException;
import com.example.api.rest.sem.web01.model.entity.Pauta;
import com.example.api.rest.sem.web01.model.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;

    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta buscarPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pauta não encontrada"));
    }
}
