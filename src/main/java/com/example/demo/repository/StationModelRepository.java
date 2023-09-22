package com.example.demo.repository;

import com.example.demo.models.StationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationModelRepository extends JpaRepository<StationModel, Long> {
}