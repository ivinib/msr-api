package com.example.vinib.msrapi.domain.service;

import com.example.vinib.msrapi.domain.exception.NegocioException;
import com.example.vinib.msrapi.domain.model.Entrega;
import com.example.vinib.msrapi.domain.model.Ocorrencia;
import com.example.vinib.msrapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }
}
