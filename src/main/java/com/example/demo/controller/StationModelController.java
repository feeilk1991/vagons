package com.example.demo.controller;

import com.example.demo.models.StationModel;
import com.example.demo.repository.StationModelRepository;
import com.example.demo.service.StationModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/station-models")
@PreAuthorize("hasRole('USER')") // Применяется ко всем методам контроллера

public class StationModelController {
    @Autowired
    private StationModelService stationModelService;
    @Autowired
    private StationModelRepository stationModelRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StationModel> findStationModel() {
        return stationModelService.getAllStationModels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationModel>  findStationModelById (@PathVariable Long id) {
        Optional<StationModel> stationModel = stationModelService.getStationModelById(id);
        return stationModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationModel> createStationModel (@RequestBody StationModel stationModel) {
        stationModelService.createStationModel(stationModel);
        return ResponseEntity.ok(stationModel);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationModel> updateStationModel(
            @PathVariable Long id,
            @RequestBody StationModel stationModel
    ) {
        stationModelService.updateStationModel(id, stationModel);
        return ResponseEntity.ok(stationModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStation (@PathVariable Long id) {
        stationModelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
