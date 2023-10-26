package com.reports.hibernate.entity.loading.lazy;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.loading.lazy.batch.LazyBatchCollectionEntity;
import com.reports.hibernate.model.entity.loading.lazy.batch.LazyBatchReferencedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.loading.lazy.batch") // scan only required entities
@DisplayName("Lazy loading with batch size inheritance")
@Sql("/sql.lazy/batchInit.sql")
public class LazyBatchTest extends BaseTest {

    @Test
    @DisplayName("Single parent fetch without association")
    void singleParentFetchWithoutChildren() {
        session.get(LazyBatchCollectionEntity.class, 1);
        AssertQueryCount.assertSelectCount(1);
    }

    @Test
    @DisplayName("Single parent fetch")
    void singleParentFetch() {
        for (LazyBatchReferencedEntity child : session.get(LazyBatchCollectionEntity.class, 1)
                .getReferencedEntities()) {
            System.out.println(child);
        }
        AssertQueryCount.assertSelectCount(2);
    }

    @Test
    @DisplayName("Multiple parents fetch")
    void multipleParentFetch() {
        List<LazyBatchCollectionEntity> entities =
                session.createQuery("from LazyBatchCollectionEntity", LazyBatchCollectionEntity.class)
                        .getResultList();
        for (LazyBatchCollectionEntity parent : entities) {
            for (LazyBatchReferencedEntity child : parent.getReferencedEntities()) {
                System.out.println(child);
            }
        }
        AssertQueryCount.assertSelectCount(1 + (entities.size() / LazyBatchCollectionEntity.BATCH_SIZE));
    }

}
