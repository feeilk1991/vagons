package com.example.demo.service;

import com.example.demo.models.PassportWagon;
import com.example.demo.models.TrainComposition;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface TrainCompositionService {
    @Nonnull
    public TrainComposition createTrainComposition(List<PassportWagon> wagons);
    public void rearrangeWagons(Long trainCompositionId, List<Long> wagonIds, String destination);
    public void departureFromStation(Long trainCompositionId);

}