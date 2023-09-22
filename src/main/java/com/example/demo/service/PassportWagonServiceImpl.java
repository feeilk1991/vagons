package com.example.demo.service;

import com.example.demo.models.PassportWagon;
import com.example.demo.repository.PassportWagonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassportWagonServiceImpl implements PassportWagonService {

    @Autowired
    private PassportWagonRepository passportWagonRepository;
    @Override
    public List<PassportWagon> getAllPassportWagons() {
        return passportWagonRepository.findAll();
    }

    @Override
    public Optional<PassportWagon> getPassportWagonById(Long id) {
        return passportWagonRepository.findById(id);
    }

    public PassportWagon createPassportWagon(PassportWagon passportWagon) {
        return passportWagonRepository.save(passportWagon);
    }

    @Override
    public PassportWagon updatePassportWagon(Long id, PassportWagon updatedPassportWagon) {
        Optional<PassportWagon> existingPassportWagon = passportWagonRepository.findById(id);
        if (existingPassportWagon.isPresent()) {
            PassportWagon passportWagon = existingPassportWagon.get();
            passportWagon.setNumber(updatedPassportWagon.getNumber());
            passportWagon.setType(updatedPassportWagon.getType());
            passportWagon.setTareWeight(updatedPassportWagon.getTareWeight());
            passportWagon.setCapacity(updatedPassportWagon.getCapacity());
            return passportWagonRepository.save(passportWagon);
        } else {
            throw new NullPointerException("PassportWagon not found with id: " + id);
        }
    }

    @Override
    public void deletePassportWagon(Long id) {
        try {
            passportWagonRepository.deleteById(id);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("PassportWagon not found with id: " + id);
        }
    }
}
