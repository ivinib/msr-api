package com.example.vinib.msrapi.api.assembler;

import com.example.vinib.msrapi.api.model.EntregaModel;
import com.example.vinib.msrapi.api.model.input.EntregaInputModel;
import com.example.vinib.msrapi.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaAssembler {

    private ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega){

        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInputModel entregaInput){
        return modelMapper.map(entregaInput, Entrega.class);
    }
}
