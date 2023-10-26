package com.reports.hibernate.model.entity.collection.collection;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class DefaultCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<DefaultReferencedEntity> referencedEntities;

}
