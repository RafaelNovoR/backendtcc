package com.ibgepopulation.tracker.DTOs;

import lombok.Data;

@Data
public class UFDTO {
    private int id;
    private String sigla;
    private String nome;
    private RegiaoDTO regiao;
}
