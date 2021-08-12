package com.example.vinib.msrapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OcorrenciaInputModel {

    @NotBlank
    private String descricao;
}
