package com.ibgepopulation.tracker.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegiaoIntermediariaDTO {
    private int id;
    private String nome;
    @JsonProperty("UF")
    private UFDTO uf;
}
