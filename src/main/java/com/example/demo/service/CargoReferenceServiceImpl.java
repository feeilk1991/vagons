package com.example.demo.service;

import com.example.demo.models.CargoReference;
import com.example.demo.repository.CargoReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargoReferenceServiceImpl implements CargoReferenceService {
    @Autowired
    CargoReferenceRepository cargoReferenceRepository;

    @Override
    public List<CargoReference> getCargoReferenceList() {
        return cargoReferenceRepository.findAll();
    }

    @Override
    public Optional<CargoReference> getCargoReferenceId(Long id) {
        return cargoReferenceRepository.findById(id);
    }

    @Override
    public void createCargoReference (CargoReference cargoReference) {
        cargoReferenceRepository.save(cargoReference);
    }

    @Override
    public void updateCargoReference (Long id, CargoReference updateCargoReference) {
        Optional<CargoReference> optionalCargoReference = cargoReferenceRepository.findById(id);
        if (optionalCargoReference.isPresent()) {
            CargoReference cargoReference = optionalCargoReference.get();
            cargoReference.setCargoName(updateCargoReference.getCargoName());
            cargoReference.setCargoCode(updateCargoReference.getCargoCode());
            cargoReference.setCargoManifests(updateCargoReference.getCargoManifests());

            cargoReferenceRepository.save(cargoReference);
        } else {
            throw new NullPointerException("Cargo reference not found with id: " + id);
        }
    }

    @Override
    public void deleteCargoReference(Long id) {
        try {
            cargoReferenceRepository.deleteById(id);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Cargo manifest not found with id " + id);
        }
    }
}
