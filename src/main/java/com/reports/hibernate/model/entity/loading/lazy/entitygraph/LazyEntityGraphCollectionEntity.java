package com.reports.hibernate.model.entity.loading.lazy.entitygraph;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@NamedEntityGraph(
    name = "graph.lazyEntityGraphCollectionEntity.firstReferencedEntities",
    attributeNodes = {
            @NamedAttributeNode("firstReferencedEntities"),
    }
)
@Entity
@Data
public class LazyEntityGraphCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<LazyEntityGraphFirstReferencedEntity> firstReferencedEntities;

    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<LazyEntityGraphSecondReferencedEntity> secondReferencedEntities;

}
