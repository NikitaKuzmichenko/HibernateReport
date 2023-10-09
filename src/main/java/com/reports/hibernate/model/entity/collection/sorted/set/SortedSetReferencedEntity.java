package com.reports.hibernate.model.entity.collection.sorted.set;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SortedSetReferencedEntity implements Comparable<SortedSetReferencedEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    private SortedSetCollectionEntity collectionEntity;

    public SortedSetReferencedEntity(String name, SortedSetCollectionEntity collectionEntity) {
        this.name = name;
        this.collectionEntity = collectionEntity;
    }

    @Override
    public int compareTo(SortedSetReferencedEntity o) {
        if (name == null && o.getName() == null) {
            return 0;
        }
        if (name == null) {
            return 1;
        }
        return -name.compareTo(o.getName());

    }

}
