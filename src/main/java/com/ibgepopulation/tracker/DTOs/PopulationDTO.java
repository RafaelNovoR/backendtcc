package com.ibgepopulation.tracker.DTOs;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PopulationDTO implements Serializable {

    private static final long serialVersionUID = 9114854159630614064L;

    private int id;
    private String nome;
    private MicrorregiaoDTO microrregiao;
    @JsonProperty("regiao-imediata")
    private RegiaoImediataDTO regiaoImediata;
}
