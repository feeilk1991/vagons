package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Cостав вагонов
 */
@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class TrainComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "trainComposition", cascade = CascadeType.ALL)
    private List<PassportWagon> wagons;

}
