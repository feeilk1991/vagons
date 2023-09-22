package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CargoManifest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sequenceNumber;
    private String wagonNumber;
    private String cargoName;
    private double cargoWeight;
    private double wagonWeight;
    @ManyToOne
    @JoinColumn(name = "passport_wagon_id")
    private PassportWagon passportWagon;

    @ManyToOne
    @JoinColumn(name = "cargo_reference_id")
    private CargoReference cargoReference;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CargoManifest that = (CargoManifest) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
