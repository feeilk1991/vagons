package com.example.demo.controller;

import com.example.demo.models.CargoManifest;
import com.example.demo.service.CargoManifestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cargo-manifest")
@PreAuthorize("hasRole('USER')") // Применяется ко всем методам контроллера

public class CargoManifestController {
    @Autowired
    private CargoManifestService cargoManifestService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CargoManifest> findCargoManifests() {
        return cargoManifestService.getAllCargoManifests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoManifest> cargoManifestResponseEntity(@PathVariable Long id) {
        Optional<CargoManifest> cargoManifest = cargoManifestService.getCargoManifestById(id);
        return cargoManifest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoManifest> createCargoManifest (@RequestBody CargoManifest cargoManifest) {
        cargoManifestService.createCargoManifest(cargoManifest);
        return ResponseEntity.ok(cargoManifest);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoManifest> updateCargoManifest(
            @PathVariable Long id,
            @RequestBody CargoManifest cargoManifest
    ) {
        cargoManifestService.updateCargoManifest(id, cargoManifest);
        return ResponseEntity.ok(cargoManifest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCargoManifest(@PathVariable Long id) {
        cargoManifestService.deleteCargoManifest(id);
        return ResponseEntity.noContent().build();
    }
}
