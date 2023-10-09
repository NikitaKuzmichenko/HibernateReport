package com.reports.hibernate.model.entity.collection.array;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ArrayCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ArrayReferencedEntity[] referencedEntities;

}
