package com.reports.hibernate.entity.loading.lazy;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.loading.lazy.entitygraph.LazyEntityGraphOwner;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.Hibernate;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.loading.lazy.entitygraph") // scan only required entities
@DisplayName("Lazy loading with EntityGraph inheritance")
@Sql("/sql.lazy/entityGraphInit.sql")
public class LazyEntityGraphTest extends BaseTest {

    @Test
    @DisplayName("Single parent fetch without graph")
    void singleParentFetchWithoutChildren() {
        session.find(LazyEntityGraphOwner.class, 1);
        AssertQueryCount.assertSelectCount(1);
    }

    @Test
    @DisplayName("Single parent fetch with load semantic")
    void singleParentLoad() {
        LazyEntityGraphOwner entity = session.byId(LazyEntityGraphOwner.class)
                .with(getEntityGraph(), GraphSemantic.LOAD)
                .load(1);
        assertAll(
                () -> assertTrue(Hibernate.isInitialized(entity.getCats())),
                () -> assertTrue(Hibernate.isInitialized(entity.getDogs())),
                () -> AssertQueryCount.assertSelectCount(2)
        );
    }

    @Test
    @DisplayName("Multiple parents fetch with load semantic")
    void multipleParentLoad() {
        List<LazyEntityGraphOwner> entities =
                session.createQuery("from LazyEntityGraphOwner", LazyEntityGraphOwner.class)
                        .applyGraph(getEntityGraph(), GraphSemantic.LOAD)
                        .getResultList();
        assertAll(
                () -> assertTrue(Hibernate.isInitialized(entities.get(0).getCats())),
                () -> assertTrue(Hibernate.isInitialized(entities.get(0).getDogs())),
                () -> AssertQueryCount.assertSelectCount(1 + entities.size())
        );
    }

    @Test
    @DisplayName("Single parent fetch with fetch semantic")
    void singleParentFetch() {
        LazyEntityGraphOwner entity = session.byId(LazyEntityGraphOwner.class)
                .with(getEntityGraph(), GraphSemantic.FETCH)
                .load(1);
        assertAll(
                () -> assertTrue(Hibernate.isInitialized(entity.getCats())),
                () -> assertFalse(Hibernate.isInitialized(entity.getDogs())),
                () -> AssertQueryCount.assertSelectCount(1)
        );
    }

    @Test
    @DisplayName("Multiple parents fetch with fetch semantic")
    void multipleParentFetch() {
        List<LazyEntityGraphOwner> entities =
                session.createQuery("from LazyEntityGraphOwner", LazyEntityGraphOwner.class)
                        .applyGraph(getEntityGraph(), GraphSemantic.FETCH)
                        .getResultList();
        assertAll(
                () -> assertTrue(Hibernate.isInitialized(entities.get(0).getCats())),
                () -> assertFalse(Hibernate.isInitialized(entities.get(0).getDogs())),
                () -> AssertQueryCount.assertSelectCount(1)
        );
    }

    private RootGraph<LazyEntityGraphOwner> getEntityGraph() {
        return (RootGraph<LazyEntityGraphOwner>)
                session.getEntityGraph("graph.lazyEntityGraphOwner.firstPets");
    }

}
