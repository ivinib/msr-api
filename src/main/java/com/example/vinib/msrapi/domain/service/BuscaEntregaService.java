package com.example.vinib.msrapi.domain.service;

import com.example.vinib.msrapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.vinib.msrapi.domain.exception.NegocioException;
import com.example.vinib.msrapi.domain.model.Entrega;
import com.example.vinib.msrapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
        return entrega;
    }


}
