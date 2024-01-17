package com.reports.hibernate.entity.loading.lazy;

import com.reports.hibernate.base.BaseTest;

import com.reports.hibernate.model.entity.loading.lazy.subselect.LazySubSelectOwner;
import com.reports.hibernate.model.entity.loading.lazy.subselect.LazySubSelectPet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.loading.lazy.subselect") // scan only required entities
@DisplayName("Lazy loading with subSelect inheritance")
@Sql("/sql.lazy/subSelectInit.sql")
public class LazySubSelectTest extends BaseTest {

    @Test
    @DisplayName("Single parent fetch without association")
    void singleParentFetchWithoutChildren() {
        session.get(LazySubSelectOwner.class, 1);
        AssertQueryCount.assertSelectCount(1);
    }

    @Test
    @DisplayName("Single parent fetch")
    void singleParentFetch() {
        for (LazySubSelectPet child : session.get(LazySubSelectOwner.class, 1)
                .getPets()) {
            System.out.println(child);
        }
        AssertQueryCount.assertSelectCount(2);
    }

    @Test
    @DisplayName("Multiple parents fetch")
    void multipleParentFetch() {
        List<LazySubSelectOwner> entities =
                session.createQuery("from LazySubSelectOwner", LazySubSelectOwner.class)
                        .getResultList();
        for (LazySubSelectOwner parent : entities) {
            for (LazySubSelectPet child : parent.getPets()) {
                System.out.println(child);
            }
        }
        AssertQueryCount.assertSelectCount(2);
    }

}
