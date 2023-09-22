package com.example.demo.repository;

import com.example.demo.models.CargoReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoReferenceRepository extends JpaRepository<CargoReference, Long> {
}