package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.sorted.map.SortedMapCollectionEntity;
import com.reports.hibernate.model.entity.collection.sorted.map.SortedMapReferencedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentSortedMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.*;

@EntityScan("com.reports.hibernate.model.entity.collection.sorted.map") // scan only required entities
@DisplayName("SortedSet collection usage")
class SortedMapCollectionTests extends BaseTest {
    @Test
    @DisplayName("Insert entity with sortedSet collection")
    void createAndGetEntity() {
        SortedMapCollectionEntity entity = new SortedMapCollectionEntity();
        SortedMap<String, SortedMapReferencedEntity> originalCollection = new TreeMap<>(Map.of(
                "referencedEntity № 1", new SortedMapReferencedEntity("referencedEntity № 1", entity),
                "referencedEntity № 2" , new SortedMapReferencedEntity("referencedEntity № 2",entity)
        ));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(SortedMapCollectionEntity.class, entity.getId());
        Map<String, SortedMapReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSortedMap<String, SortedMapReferencedEntity>),
                () -> assertEquals(originalCollection.keySet(), fetchedCollection.keySet()),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
