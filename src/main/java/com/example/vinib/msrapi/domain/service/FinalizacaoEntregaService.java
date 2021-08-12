package com.example.vinib.msrapi.domain.service;

import com.example.vinib.msrapi.domain.exception.NegocioException;
import com.example.vinib.msrapi.domain.model.Entrega;
import com.example.vinib.msrapi.domain.model.StatusEntrega;
import com.example.vinib.msrapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }
}
