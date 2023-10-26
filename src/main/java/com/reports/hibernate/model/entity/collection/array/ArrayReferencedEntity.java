package com.reports.hibernate.model.entity.collection.array;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
public class ArrayReferencedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public ArrayReferencedEntity(String name, ArrayCollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
        this.name = name;
    }

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ArrayCollectionEntity collectionEntity;

}
