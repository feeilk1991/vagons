package com.example.demo.service;

import com.example.demo.models.StationModel;

import java.util.List;
import java.util.Optional;
public interface StationModelService {
    public List<StationModel> getAllStationModels();
    public Optional<StationModel> getStationModelById(Long id);
    public void createStationModel(StationModel StationModel);
    public void updateStationModel(Long id, StationModel updatedStationModel);
    public void deleteStationModel(Long id);
}
