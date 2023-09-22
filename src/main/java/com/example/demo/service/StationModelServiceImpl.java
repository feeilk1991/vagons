package com.example.demo.service;

import com.example.demo.models.StationModel;
import com.example.demo.repository.StationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class StationModelServiceImpl implements StationModelService {

    @Autowired
    StationModelRepository stationModelRepository;
    @Override
    public List<StationModel> getAllStationModels() {
        return stationModelRepository.findAll();
    }

    @Override
    public Optional<StationModel> getStationModelById(Long id) {
        return stationModelRepository.findById(id);
    }

    @Override
    public void createStationModel(StationModel stationModel) {
        stationModelRepository.save(stationModel);
    }

    @Override
    public void updateStationModel(Long id, StationModel updatedStationModel) {
        Optional<StationModel> optionalStationModel = stationModelRepository.findById(id);
        if (optionalStationModel.isPresent()) {
            StationModel stationModel = optionalStationModel.get();
            stationModel.setStationName(updatedStationModel.getStationName());
            stationModel.setTrackName(updatedStationModel.getTrackName());
            stationModel.setPassportWagons(updatedStationModel.getPassportWagons());

            stationModelRepository.save(updatedStationModel);
        } else {
            throw new NullPointerException("Station not found with id: " + id);
        }
    }

    @Override
    public void deleteStationModel(Long id) {
        try {
            stationModelRepository.deleteById(id);
        } catch (NullPointerException exception) {
            throw new NullPointerException("Station not found with id: " + id);
        }
    }
}
