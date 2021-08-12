package com.example.vinib.msrapi.api.controller;

import com.example.vinib.msrapi.api.assembler.ClienteAssembler;
import com.example.vinib.msrapi.api.model.ClienteModel;
import com.example.vinib.msrapi.api.model.input.ClienteInputModel;
import com.example.vinib.msrapi.domain.model.Cliente;
import com.example.vinib.msrapi.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.vinib.msrapi.domain.repository.ClienteRepository;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;
    private ClienteAssembler clienteAssembler;

    @GetMapping
    public List<ClienteModel> listar(){

        return clienteAssembler.toCollectionModel(clienteRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId){

        return clienteRepository.findById(clienteId)
                .map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@Valid @RequestBody ClienteInputModel clienteInput){
        Cliente novoCliente = clienteAssembler.toEntity(clienteInput);
        Cliente salvarCliente = catalogoClienteService.salvar(novoCliente);
        return clienteAssembler.toModel(salvarCliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar (@PathVariable Long clienteId,
                                              @Valid @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);

        cliente = catalogoClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){

        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        catalogoClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }
}
