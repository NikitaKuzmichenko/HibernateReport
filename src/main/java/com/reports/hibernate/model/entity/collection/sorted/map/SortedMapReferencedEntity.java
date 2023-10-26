package com.reports.hibernate.model.entity.collection.sorted.map;

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
public class SortedMapReferencedEntity implements Comparable<SortedMapReferencedEntity>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    private SortedMapCollectionEntity collectionEntity;

    public SortedMapReferencedEntity(String name, SortedMapCollectionEntity collectionEntity) {
        this.name = name;
        this.collectionEntity = collectionEntity;
    }

    @Override
    public int compareTo(SortedMapReferencedEntity o) {
        if(name == null && o.getName() == null){
            return 0;
        }
        if(name == null){
            return 1;
        }
        return -name.compareTo(o.getName());
    }

}
