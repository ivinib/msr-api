package com.example.vinib.msrapi.api.controller;

import com.example.vinib.msrapi.api.assembler.EntregaAssembler;
import com.example.vinib.msrapi.api.model.EntregaModel;
import com.example.vinib.msrapi.api.model.input.EntregaInputModel;
import com.example.vinib.msrapi.domain.model.Entrega;
import com.example.vinib.msrapi.domain.repository.EntregaRepository;
import com.example.vinib.msrapi.domain.service.FinalizacaoEntregaService;
import com.example.vinib.msrapi.domain.service.SolicitaEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitaEntregaService solicitaEntregaService;
    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler;
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInputModel entregaInput){
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitaEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaModel> listar(){

        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);
    }
}
