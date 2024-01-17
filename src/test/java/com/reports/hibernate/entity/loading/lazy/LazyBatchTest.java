package com.reports.hibernate.entity.loading.lazy;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.loading.lazy.batch.LazyBatchOwner;
import com.reports.hibernate.model.entity.loading.lazy.batch.LazyBatchPet;
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
        session.get(LazyBatchOwner.class, 1);
        AssertQueryCount.assertSelectCount(1);
    }

    @Test
    @DisplayName("Single parent fetch")
    void singleParentFetch() {
        for (LazyBatchPet child : session.get(LazyBatchOwner.class, 1).getPets()) {
            System.out.println(child);
        }
        AssertQueryCount.assertSelectCount(2);
    }

    @Test
    @DisplayName("Multiple parents fetch")
    void multipleParentFetch() {
        List<LazyBatchOwner> entities =
                session.createQuery("from LazyBatchOwner", LazyBatchOwner.class)
                        .getResultList();
        for (LazyBatchOwner parent : entities) {
            for (LazyBatchPet child : parent.getPets()) {
                System.out.println(child);
            }
        }
        AssertQueryCount.assertSelectCount(1 + (entities.size() / LazyBatchOwner.BATCH_SIZE));
    }

}
