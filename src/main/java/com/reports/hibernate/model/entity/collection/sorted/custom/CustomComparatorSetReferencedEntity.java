package com.reports.hibernate.model.entity.collection.sorted.custom;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
public class CustomComparatorSetReferencedEntity implements Comparable<CustomComparatorSetReferencedEntity>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public CustomComparatorSetReferencedEntity(String name, CustomComparatorSetCollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
        this.name = name;
    }

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomComparatorSetCollectionEntity collectionEntity;


    @Override
    public int compareTo(CustomComparatorSetReferencedEntity o) {
        return this.getName().compareTo(o.getName());
    }
}
