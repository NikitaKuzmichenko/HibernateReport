package com.reports.hibernate.model.entity.collection.collection;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
public class DefaultReferencedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public DefaultReferencedEntity(String name, DefaultCollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
        this.name = name;
    }

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DefaultCollectionEntity collectionEntity;
}
