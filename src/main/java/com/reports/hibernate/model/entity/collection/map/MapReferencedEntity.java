package com.reports.hibernate.model.entity.collection.map;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MapReferencedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public MapReferencedEntity(String name, MapCollectionEntity collectionEntity) {
        this.name = name;
        this.collectionEntity = collectionEntity;
    }

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MapCollectionEntity collectionEntity;

}
