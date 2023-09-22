package com.example.demo.service;

import com.example.demo.models.CargoReference;

import java.util.List;
import java.util.Optional;
public interface CargoReferenceService {
    public List<CargoReference> getCargoReferenceList();
    public Optional<CargoReference> getCargoReferenceId(Long id);
    public void createCargoReference(CargoReference CargoManifest);
    public void updateCargoReference(Long id, CargoReference updatedCargoManifest);
    public void deleteCargoReference(Long id);
}
