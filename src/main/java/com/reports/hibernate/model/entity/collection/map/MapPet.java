package com.reports.hibernate.model.entity.collection.map;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MapPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public MapPet(String name, MapOwner owner) {
        this.name = name;
        this.owner = owner;
    }

    @ManyToOne()
    @JoinColumn(name = "ownerId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MapOwner owner;

}
