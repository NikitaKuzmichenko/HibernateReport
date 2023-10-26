package com.reports.hibernate.model.entity.collection.sorted.set;

import jakarta.persistence.*;
import lombok.*;

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

    public SortedSetReferencedEntity(String name, SortedSetCollectionEntity collectionEntity) {
        this.name = name;
        this.collectionEntity = collectionEntity;
    }

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SortedSetCollectionEntity collectionEntity;

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
