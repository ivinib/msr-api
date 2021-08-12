package com.example.vinib.msrapi.domain.service;

import com.example.vinib.msrapi.domain.exception.NegocioException;
import com.example.vinib.msrapi.domain.model.Cliente;
import com.example.vinib.msrapi.domain.model.Entrega;
import com.example.vinib.msrapi.domain.model.StatusEntrega;
import com.example.vinib.msrapi.domain.repository.ClienteRepository;
import com.example.vinib.msrapi.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitaEntregaService {

    private EntregaRepository entregaRepository;
    private CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega){

        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
