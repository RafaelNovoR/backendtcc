package com.ibgepopulation.tracker.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class PopulationResultDTO {
    private String cityId;
    private List<PopulationYearDTO> populationData;

}
