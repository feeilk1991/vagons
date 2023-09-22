package com.example.demo.controller;

import com.example.demo.models.PassportWagon;
import com.example.demo.service.PassportWagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passport-wagons")
@PreAuthorize("hasRole('USER')") // Применяется ко всем методам контроллера
public class PassportWagonController {
    @Autowired
    private PassportWagonService passportWagonService;

    @GetMapping
    public List<PassportWagon> getAllPassportWagons() {
        return passportWagonService.getAllPassportWagons();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassportWagon> getPassportWagonById(@PathVariable Long id) {
        Optional<PassportWagon> passportWagon = passportWagonService.getPassportWagonById(id);
        return passportWagon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassportWagon> createPassportWagon(@RequestBody PassportWagon passportWagon) {
        passportWagonService.createPassportWagon(passportWagon);
        return ResponseEntity.ok(passportWagon);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassportWagon> updatePassportWagon(
            @PathVariable Long id,
            @RequestBody PassportWagon updatedPassportWagon
    ) {
        passportWagonService.updatePassportWagon(id, updatedPassportWagon);
        return ResponseEntity.ok(updatedPassportWagon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassportWagon(@PathVariable Long id) {
        passportWagonService.deletePassportWagon(id);
        return ResponseEntity.noContent().build();
    }
}
