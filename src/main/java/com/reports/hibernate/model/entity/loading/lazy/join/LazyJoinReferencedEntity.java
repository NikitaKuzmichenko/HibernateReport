package com.reports.hibernate.model.entity.loading.lazy.join;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
public class LazyJoinReferencedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "collectionEntityId", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LazyJoinCollectionEntity collectionEntity;

}
