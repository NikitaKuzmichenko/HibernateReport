package com.reports.hibernate.model.entity.collection.list;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ListCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ListReferencedEntity> referencedEntities;

}
