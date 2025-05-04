package com.ibgepopulation.tracker.DTOs;

import lombok.Data;

@Data
public class MicrorregiaoDTO {
    private int id;
    private String nome;
    private MesorregiaoDTO mesorregiao;
}
