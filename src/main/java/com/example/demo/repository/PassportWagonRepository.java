package com.example.demo.repository;

import com.example.demo.models.PassportWagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportWagonRepository extends JpaRepository<PassportWagon, Long> {

}