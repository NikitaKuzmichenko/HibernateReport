package com.reports.hibernate.model.entity.loading.lazy.subselect;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
public class LazySubSelectCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "collectionEntity", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<LazySubSelectReferencedEntity> referencedEntities;

}
