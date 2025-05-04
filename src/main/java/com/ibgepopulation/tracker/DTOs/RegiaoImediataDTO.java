package com.ibgepopulation.tracker.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegiaoImediataDTO {
    private int id;
    private String nome;
     @JsonProperty("regiao-intermediaria")
    private RegiaoIntermediariaDTO regiaoIntermediaria;
}
