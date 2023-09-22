package com.example.demo.repository;

import com.example.demo.models.TrainComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCompositionRepository extends JpaRepository<TrainComposition, Long> {
}
