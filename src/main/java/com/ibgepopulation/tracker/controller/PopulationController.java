package com.ibgepopulation.tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibgepopulation.tracker.DTOs.PopulationDTO;
import com.ibgepopulation.tracker.DTOs.PopulationResultDTO;
import com.ibgepopulation.tracker.service.PopulationService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/population")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Population API", description = "API for managing population data")
@OpenAPIDefinition(info = @Info(title = "Population Management API", version = "1.0", description = "This API provides endpoints for managing population data for different cities.", contact = @Contact(name = "API Support", email = "support@example.com", url = "http://example.com/contact"), license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")), servers = {
        @Server(url = "http://localhost:8080", description = "Local server"),
        @Server(url = "https://api.example.com", description = "Production server")
})
public class PopulationController {

    public final PopulationService populationService;

    // Get population data for all cities
    @Operation(summary = "Get population data for all cities", description = "Fetches a list of population data for every city in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Population data retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No content, no population data available")
    })
    @GetMapping
    public ResponseEntity<List<PopulationDTO>> getAllPopulationData() {
        List<PopulationDTO> populationData = populationService.getAllPopulationData();

        if (populationData.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if there's no data
        }

        return ResponseEntity.ok(populationData); // Return 200 with the population data
    }

    // Get population data by city ID
    @Operation(summary = "Get population data for a specific city", description = "Retrieves population data by city ID.")
    @Parameter(name = "cityId", description = "The unique identifier of the city", required = true)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Population data for the city retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    @GetMapping("/{cityId}")
    public ResponseEntity<PopulationResultDTO> getPopulationDataByCity(@PathVariable String cityId) {
        PopulationResultDTO populationData = populationService.getPopulationDataByCity(cityId);

        if (populationData != null) {
            return ResponseEntity.ok(populationData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Calculate growth rate for a specific city
    @Operation(summary = "Calculate population growth rate", description = "Calculates the growth rate for a specific city using its ID.")
    @Parameter(name = "cityId", description = "The unique identifier of the city", required = true)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Growth rate calculated successfully"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    @GetMapping("/growth-rate/{cityId}")
    public ResponseEntity<Double> calculateGrowthRate(@PathVariable String cityId) {
        return null; // Placeholder for the actual implementation
    }

    // Add new population data for a city
    @PostMapping
    public ResponseEntity<String> addPopulationData(@RequestBody PopulationDTO populationDTO) {
        // Mock implementation: return a success message
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Population data for city added successfully");
    }

    // Update population data for a specific city
    @PutMapping("/{cityId}")
    public ResponseEntity<String> updatePopulationData(@PathVariable String cityId,
            @RequestBody PopulationDTO populationDTO) {
        // Mock implementation: return a success message
        return ResponseEntity.ok("Population data for city " + cityId + " updated successfully");
    }

    // Modify part of the population data for a specific city
    @PatchMapping("/{cityId}")
    public ResponseEntity<String> patchPopulationData(@PathVariable String cityId,
            @RequestBody PopulationDTO populationDTO) {
        // Mock implementation: return a success message
        return ResponseEntity.ok("Population data for city " + cityId + " partially updated successfully");
    }

    // Delete population data for a specific city
    @DeleteMapping("/{cityId}")
    public ResponseEntity<String> deletePopulationData(@PathVariable String cityId) {
        // Mock implementation: return a success message
        return ResponseEntity.ok("Population data for city " + cityId + " deleted successfully");
    }

}
