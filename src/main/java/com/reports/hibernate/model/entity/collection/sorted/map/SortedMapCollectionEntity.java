package com.reports.hibernate.model.entity.collection.sorted.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.SortedMap;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SortedMapCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @MapKeyColumn(name = "name")
    private SortedMap<String, SortedMapReferencedEntity> referencedEntities;

}
