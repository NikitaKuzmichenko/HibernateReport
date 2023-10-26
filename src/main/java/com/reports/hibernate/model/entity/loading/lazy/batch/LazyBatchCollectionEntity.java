package com.reports.hibernate.model.entity.loading.lazy.batch;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Data
public class LazyBatchCollectionEntity {

    public static final int BATCH_SIZE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @BatchSize(size = LazyBatchCollectionEntity.BATCH_SIZE)
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "collectionEntity", fetch = FetchType.LAZY)
    private List<LazyBatchReferencedEntity> referencedEntities;

}
