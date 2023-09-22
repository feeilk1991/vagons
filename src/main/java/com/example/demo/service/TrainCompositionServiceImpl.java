package com.example.demo.service;

import com.example.demo.models.PassportWagon;
import com.example.demo.models.TrainComposition;
import com.example.demo.repository.PassportWagonRepository;
import com.example.demo.repository.TrainCompositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TrainCompositionServiceImpl implements TrainCompositionService{
    @Autowired
    PassportWagonRepository passportWagonRepository;
    @Autowired
    TrainCompositionRepository trainCompositionRepository;

    @Override
    public TrainComposition createTrainComposition(List<PassportWagon> wagons) {
        TrainComposition trainComposition = new TrainComposition();
        trainComposition.setWagons(wagons);

        for (PassportWagon wagon : wagons) {
            wagon.setTrainComposition(trainComposition);
        }

        return trainCompositionRepository.save(trainComposition);
    }

    @Override
    public void rearrangeWagons(Long trainCompositionId, List<Long> wagonIds, String destination) {
        TrainComposition trainComposition = trainCompositionRepository.findById(trainCompositionId)
                .orElseThrow(() -> new NullPointerException("TrainComposition not found with id: " + trainCompositionId));

        List<PassportWagon> wagonsToMove = new ArrayList<>();
        List<PassportWagon> wagonsInComposition = trainComposition.getWagons();

        for (Long wagonId : wagonIds) {
            PassportWagon wagon = passportWagonRepository.findById(wagonId)
                    .orElseThrow(() -> new NullPointerException("Wagon not found with id: " + wagonId));
            if (wagonsInComposition.contains(wagon)) {
                wagonsToMove.add(wagon);
                wagonsInComposition.remove(wagon);
            }
        }

        String begin = "begin";
        String end = "end";
        if (begin.equalsIgnoreCase(destination)) {
            wagonsInComposition.addAll(0, wagonsToMove);
        } else if (end.equalsIgnoreCase(destination)) {
            wagonsInComposition.addAll(wagonsToMove);
        } else {
            throw new IllegalArgumentException("Invalid destination: " + destination);
        }

        trainCompositionRepository.save(trainComposition);
    }

    @Override
    public void departureFromStation(Long trainCompositionId) {
        TrainComposition trainComposition = trainCompositionRepository.findById(trainCompositionId)
                .orElseThrow(() -> new NullPointerException("TrainComposition not found with id: " + trainCompositionId));

        List<PassportWagon> wagonsInComposition = trainComposition.getWagons();

        if (!wagonsInComposition.isEmpty()) {
            wagonsInComposition.remove(0); // Убираем вагон с начала состава
            trainCompositionRepository.save(trainComposition);
        } else {
            throw new IllegalStateException("TrainComposition is empty. No wagons to depart.");
        }
    }
}
