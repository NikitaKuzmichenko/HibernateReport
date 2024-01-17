package com.reports.hibernate.entity.loading.lazy;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.loading.lazy.select.LazySelectOwner;
import com.reports.hibernate.model.entity.loading.lazy.select.LazySelectPet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.loading.lazy.select") // scan only required entities
@DisplayName("Lazy loading with Select inheritance")
@Sql("/sql.lazy/selectInit.sql")
public class LazySelectTest extends BaseTest {

    @Test
    @DisplayName("Single parent fetch without association")
    void singleParentFetchWithoutChildren() {
        session.get(LazySelectOwner.class, 1);
        AssertQueryCount.assertSelectCount(1);
    }

    @Test
    @DisplayName("Single parent fetch")
    void singleParentFetch() {
        for (LazySelectPet child : session.get(LazySelectOwner.class, 1)
                .getPets()) {
            System.out.println(child);
        }
        AssertQueryCount.assertSelectCount(2);
    }

    @Test
    @DisplayName("Multiple parents fetch")
    void multipleParentFetch() {
        List<LazySelectOwner> entities =
                session.createQuery("from LazySelectOwner", LazySelectOwner.class)
                        .getResultList();

        for (LazySelectOwner parent : entities) {
            for (LazySelectPet child : parent.getPets()) {
                System.out.println(child);
            }
        }
        AssertQueryCount.assertSelectCount(1 + entities.size());
    }

}
