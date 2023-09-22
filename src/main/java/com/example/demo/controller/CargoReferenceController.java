package com.example.demo.controller;

import com.example.demo.models.CargoReference;
import com.example.demo.service.CargoReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cargo-reference")
@PreAuthorize("hasRole('USER')") // Применяется ко всем методам контроллера
public class CargoReferenceController {
    @Autowired
    private CargoReferenceService cargoReferenceService;

    public CargoReferenceController() {

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CargoReference> cargoReferenceList() {
        return cargoReferenceService.getCargoReferenceList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoReference> cargoReferenceResponseEntity(@PathVariable Long id) {
        Optional<CargoReference> cargoReference = cargoReferenceService.getCargoReferenceId(id);
        return cargoReference.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoReference> createCargoReference (@RequestBody CargoReference CargoReference) {
        cargoReferenceService.createCargoReference(CargoReference);
        return ResponseEntity.ok(CargoReference);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CargoReference> updateCargoReference(
            @PathVariable Long id,
            @RequestBody CargoReference CargoReference
    ) {
        cargoReferenceService.updateCargoReference(id, CargoReference);
        return ResponseEntity.ok(CargoReference);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCargoReference(@PathVariable Long id) {
        cargoReferenceService.deleteCargoReference(id);
        return ResponseEntity.noContent().build();
    }
}
