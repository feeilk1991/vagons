package com.example.demo.service;

import com.example.demo.models.CargoManifest;
import com.example.demo.repository.CargoManifestRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CargoManifestServiceImpl implements CargoManifestService{

    @Autowired
    private CargoManifestRepository cargoManifestRepository;

    @Override
    public List<CargoManifest> getAllCargoManifests() {
        return cargoManifestRepository.findAll();
    }

    @Override
    public Optional<CargoManifest> getCargoManifestById(Long id) {
        return cargoManifestRepository.findById(id);
    }

    @Override
    public CargoManifest createCargoManifest(CargoManifest cargoManifest) {
        return cargoManifestRepository.save(cargoManifest);
    }

    @Override
    public CargoManifest updateCargoManifest(Long id, CargoManifest updatedCargoManifest) {
        Optional<CargoManifest> optionalCargoManifest = cargoManifestRepository.findById(id);
        if (optionalCargoManifest.isPresent()) {
            CargoManifest cargoManifest = optionalCargoManifest.get();
            cargoManifest.setCargoName(cargoManifest.getCargoName());
            cargoManifest.setWagonNumber(updatedCargoManifest.getWagonNumber());
            cargoManifest.setCargoReference(updatedCargoManifest.getCargoReference());
            cargoManifest.setCargoWeight(updatedCargoManifest.getCargoWeight());
            cargoManifest.setSequenceNumber(updatedCargoManifest.getSequenceNumber());

            return cargoManifestRepository.save(cargoManifest);
        } else {
            throw new NullPointerException("Cargo manifest not found with id: " + id);
        }
    }

    @Override
    public void deleteCargoManifest(Long id) {
        try {
            cargoManifestRepository.deleteById(id);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Carbon manifest not found with id " + id);
        }
    }
}
