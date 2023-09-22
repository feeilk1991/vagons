package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PassportWagon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String type;
    private double tareWeight;
    private double capacity;

    @ManyToOne
    @JoinColumn(name = "station_model_id")
    private StationModel stationModel;

    @ManyToOne
    @JoinColumn(name = "train_composition_id")
    private TrainComposition trainComposition;

    @OneToMany(mappedBy = "passportWagon", cascade = CascadeType.ALL)
    private List<CargoManifest> cargoManifests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PassportWagon that = (PassportWagon) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
