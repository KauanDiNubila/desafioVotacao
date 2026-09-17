package com.example.api.rest.sem.web01.service;

import com.example.api.rest.sem.web01.model.DTO.AssociadoRequestDTO;
import com.example.api.rest.sem.web01.model.entity.Associado;
import com.example.api.rest.sem.web01.model.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public Associado criar(AssociadoRequestDTO dto) {
        Associado associado = new Associado();
        associado.setNome(dto.getNome());
        associado.setCpf(dto.getCpf());

        return associadoRepository.save(associado);
    }

    public List<Associado> listar() {
        return associadoRepository.findAll();
    }
}
