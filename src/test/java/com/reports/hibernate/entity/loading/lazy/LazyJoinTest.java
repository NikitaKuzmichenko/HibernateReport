package com.reports.hibernate.entity.loading.lazy;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.loading.lazy.join.LazyJoinCollectionEntity;
import com.reports.hibernate.model.entity.loading.lazy.join.LazyJoinReferencedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.loading.lazy.join") // scan only required entities
@DisplayName("Lazy loading with Join inheritance")
@Sql("/sql.lazy/joinInit.sql")
public class LazyJoinTest extends BaseTest {

    @Test
    @DisplayName("Single parent fetch without association")
    void singleParentFetchWithoutChildren() {
        session.get(LazyJoinCollectionEntity.class, 1);
        AssertQueryCount.assertSelectCount(1);
    }

    @Test
    @DisplayName("Single parent fetch")
    void singleParentFetch() {
        for (LazyJoinReferencedEntity child : session.get(LazyJoinCollectionEntity.class, 1)
                .getReferencedEntities()) {
            System.out.println(child);
        }
        AssertQueryCount.assertSelectCount(1);
    }

    @Test
    @DisplayName("Multiple parents fetch")
    void multipleParentFetch() {
        List<LazyJoinCollectionEntity> entities =
                session.createQuery("from LazyJoinCollectionEntity", LazyJoinCollectionEntity.class)
                        .getResultList();
        for (LazyJoinCollectionEntity parent : entities) {
            for (LazyJoinReferencedEntity child : parent.getReferencedEntities()) {
                System.out.println(child);
            }
        }
        AssertQueryCount.assertSelectCount(1 + entities.size());
    }

}
