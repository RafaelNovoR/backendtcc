package com.ibgepopulation.tracker;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ibgepopulation.tracker.DTOs.PopulationDTO;
import com.ibgepopulation.tracker.DTOs.PopulationResultDTO;
import com.ibgepopulation.tracker.controller.PopulationController;
import com.ibgepopulation.tracker.service.PopulationService;

@SpringBootTest
public class PopulationControllerTest {

    @Mock
    private PopulationService populationService;

    @InjectMocks
    private PopulationController populationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Initialize the mock and controller
        mockMvc = MockMvcBuilders.standaloneSetup(populationController).build();
    }

    @Test
    public void testGetAllPopulationData_Returns200_WhenDataIsPresent() throws Exception {
        PopulationDTO populationDTO = new PopulationDTO(); // Create a mock PopulationDTO
        List<PopulationDTO> populationList = Collections.singletonList(populationDTO);

        when(populationService.getAllPopulationData()).thenReturn(populationList);

        mockMvc.perform(get("/api/v1/population"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());
    }

    /**
     * Test case for the scenario when no population data is available.
     * It verifies that the API responds with a 204 No Content status code.
     *
     * @throws Exception if the mock request encounters an issue
     */
    @Test
    public void testGetAllPopulationData_Returns204_WhenNoDataIsPresent() throws Exception {
        // Mock the service to return an empty list when fetching all population data
        when(populationService.getAllPopulationData()).thenReturn(Collections.emptyList());

        // Perform a GET request to the "/api/v1/population" endpoint
        mockMvc.perform(get("/api/v1/population"))
                // Expect a 204 No Content status since there is no data available
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPopulationDataByCity_Returns200_WhenCityIsFound() throws Exception {
        String cityId = "123";
        PopulationResultDTO populationResultDTO = new PopulationResultDTO();
        when(populationService.getPopulationDataByCity(cityId)).thenReturn(populationResultDTO);

        mockMvc.perform(get("/api/v1/population/{cityId}", cityId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testGetPopulationDataByCity_Returns404_WhenCityIsNotFound() throws Exception {
        String cityId = "123";
        when(populationService.getPopulationDataByCity(cityId)).thenReturn(null);

        mockMvc.perform(get("/api/v1/population/{cityId}", cityId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddPopulationData_Returns201_WhenDataIsAdded() throws Exception {
        PopulationDTO populationDTO = new PopulationDTO(); // Mock DTO
        String json = "{ \"city\": \"Sample City\", \"population\": 100000 }"; // Example request body

        mockMvc.perform(post("/api/v1/population")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Population data for city added successfully"));
    }

    @Test
    public void testUpdatePopulationData_Returns200_WhenDataIsUpdated() throws Exception {
        String cityId = "123";
        PopulationDTO populationDTO = new PopulationDTO();
        String json = "{ \"city\": \"Updated City\", \"population\": 200000 }";

        mockMvc.perform(put("/api/v1/population/{cityId}", cityId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Population data for city " + cityId + " updated successfully"));
    }

    @Test
    public void testPatchPopulationData_Returns200_WhenDataIsPartiallyUpdated() throws Exception {
        String cityId = "123";
        PopulationDTO populationDTO = new PopulationDTO();
        String json = "{ \"city\": \"Partially Updated City\", \"population\": 150000 }";

        mockMvc.perform(patch("/api/v1/population/{cityId}", cityId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Population data for city " + cityId + " partially updated successfully"));
    }

    @Test
    public void testDeletePopulationData_Returns200_WhenDataIsDeleted() throws Exception {
        String cityId = "123";

        mockMvc.perform(delete("/api/v1/population/{cityId}", cityId))
                .andExpect(status().isOk())
                .andExpect(content().string("Population data for city " + cityId + " deleted successfully"));
    }
}
