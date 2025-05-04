package com.ibgepopulation.tracker.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MesorregiaoDTO {
    private int id;
    private String nome;
    @JsonProperty("UF")
    private UFDTO UF;
}
