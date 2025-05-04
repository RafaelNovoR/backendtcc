package com.ibgepopulation.tracker.service;

import java.util.List;

import com.ibgepopulation.tracker.DTOs.PopulationDTO;
import com.ibgepopulation.tracker.DTOs.PopulationResultDTO;

public interface PopulationService {

    List<PopulationDTO> getAllPopulationData();

    PopulationResultDTO getPopulationDataByCity(String cityId);

}
