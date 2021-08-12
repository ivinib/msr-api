package com.example.vinib.msrapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteInputModel {


    private Long id;

    private String nome;

    private String email;

    private String telefone;
}
