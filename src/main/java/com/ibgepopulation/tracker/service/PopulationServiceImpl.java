package com.ibgepopulation.tracker.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibgepopulation.tracker.DTOs.PopulationDTO;
import com.ibgepopulation.tracker.DTOs.PopulationResultDTO;
import com.ibgepopulation.tracker.DTOs.PopulationYearDTO;
import com.ibgepopulation.tracker.helper.CityMapper;

@Service
public class PopulationServiceImpl implements PopulationService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON deserialization
    private static final String BASE_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/";
    private static final String BASE_URL_RESULT = "https://servicodados.ibge.gov.br/api/v1/pesquisas/-/indicadores/29171/resultados/";

    @Override
    public List<PopulationDTO> getAllPopulationData() {
        List<PopulationDTO> populations = new ArrayList<>();

        for (String cityName : CityMapper.cityIdMap.keySet()) {
            String cityId = CityMapper.getCityId(cityName);
            String url = BASE_URL + cityId;

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    // Deserialize JSON response to PopulationDTO
                    PopulationDTO populationDTO = objectMapper.readValue(response.body(), PopulationDTO.class);
                    populations.add(populationDTO);
                } else {
                    // Handle non-200 responses as needed
                    System.err.println("Error fetching data for " + cityName + ": " + response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return populations;
    }

    @Override
    public PopulationResultDTO getPopulationDataByCity(String cityId) {
        String url = BASE_URL_RESULT + cityId;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Parse the response and map to PopulationResultDTO
                JsonNode rootNode = objectMapper.readTree(response.body());
                JsonNode resultsNode = rootNode.get(0).get("res").get(0).get("res");

                List<PopulationYearDTO> populationData = new ArrayList<>();
                resultsNode.fieldNames().forEachRemaining(year -> {
                    PopulationYearDTO populationYearDTO = new PopulationYearDTO();
                    populationYearDTO.setYear(year);
                    populationYearDTO.setPopulation(resultsNode.get(year).asText());
                    populationData.add(populationYearDTO);
                });

                PopulationResultDTO populationResultDTO = new PopulationResultDTO();
                populationResultDTO.setCityId(cityId);
                populationResultDTO.setPopulationData(populationData);

                return populationResultDTO;
            } else {
                // Handle non-200 responses as needed
                System.err.println("Error fetching data for city ID " + cityId + ": " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null; // In case of failure
    }
}
