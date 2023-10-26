package com.reports.hibernate.model.entity.collection.set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SetReferencedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id;

    private String name;

    public SetReferencedEntity(String name, SetCollectionEntity collectionEntity) {
        this.name = name;
        this.collectionEntity = collectionEntity;
    }

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SetCollectionEntity collectionEntity;

}
