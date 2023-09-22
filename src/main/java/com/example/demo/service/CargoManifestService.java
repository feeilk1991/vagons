package com.example.demo.service;

import com.example.demo.models.CargoManifest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CargoManifestService {
    public List<CargoManifest> getAllCargoManifests();
    public Optional<CargoManifest> getCargoManifestById(Long id);
    public CargoManifest createCargoManifest(CargoManifest CargoManifest);
    public CargoManifest updateCargoManifest(Long id, CargoManifest updatedCargoManifest);
    public void deleteCargoManifest(Long id);
}
