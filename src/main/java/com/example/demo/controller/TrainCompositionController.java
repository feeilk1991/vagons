package com.example.demo.controller;

import com.example.demo.models.PassportWagon;
import com.example.demo.models.TrainComposition;
import com.example.demo.service.TrainCompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/train-compositions")
@PreAuthorize("hasRole('ADMIN')") // Применяется ко всем методам контроллера

public class TrainCompositionController {
    @Autowired
    private TrainCompositionService trainCompositionService;

    @PostMapping("/receive")
    public TrainComposition receiveWagons(@RequestBody List<PassportWagon> wagons) {
        return trainCompositionService.createTrainComposition(wagons);
    }

    @PostMapping("/rearrange/{trainCompositionId}")
    public ResponseEntity<String> rearrangeWagons(
            @PathVariable Long trainCompositionId,
            @RequestBody List<Long> wagonIds,
            @RequestParam String destination) {
        // Вызовите сервис для операции перестановки вагонов
        trainCompositionService.rearrangeWagons(trainCompositionId, wagonIds, destination);
        return ResponseEntity.ok("Wagons rearranged successfully.");
    }

    @PostMapping("/depart/{trainCompositionId}")
    public ResponseEntity<String> departFromStation(@PathVariable Long trainCompositionId) {
        // Вызовите сервис для операции убытия вагонов
        trainCompositionService.departureFromStation(trainCompositionId);
        return ResponseEntity.ok("Wagons departed successfully.");
    }
}
