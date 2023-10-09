package com.reports.hibernate.model.entity.collection.sorted.custom;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SortComparator;

import java.util.Set;

@Entity
@Data
public class CustomComparatorSetCollectionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @SortComparator(CustomComparator.class)
    private Set<CustomComparatorSetReferencedEntity> referencedEntities;

}
