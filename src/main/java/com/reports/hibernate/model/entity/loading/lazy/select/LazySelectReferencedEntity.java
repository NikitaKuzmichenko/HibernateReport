package com.reports.hibernate.model.entity.loading.lazy.select;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class LazySelectReferencedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public LazySelectReferencedEntity(String name, LazySelectCollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
        this.name = name;
    }

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LazySelectCollectionEntity collectionEntity;

}