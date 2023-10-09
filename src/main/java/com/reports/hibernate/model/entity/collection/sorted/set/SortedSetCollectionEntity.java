package com.reports.hibernate.model.entity.collection.sorted.set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.SortedSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SortedSetCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private SortedSet<SortedSetReferencedEntity> referencedEntities;

}
