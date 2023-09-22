package com.example.demo.service;

import com.example.demo.models.PassportWagon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PassportWagonService {
    public List<PassportWagon> getAllPassportWagons();
    public Optional<PassportWagon> getPassportWagonById(Long id);
    public PassportWagon createPassportWagon(PassportWagon passportWagon);
    public PassportWagon updatePassportWagon(Long id, PassportWagon updatedPassportWagon);
    public void deletePassportWagon(Long id);
}
