package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.sorted.map.SortedMapOwner;
import com.reports.hibernate.model.entity.collection.sorted.map.SortedMapPet;
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
        SortedMapOwner entity = new SortedMapOwner();
        SortedMap<String, SortedMapPet> originalCollection = new TreeMap<>(Map.of(
                "pet number 1", new SortedMapPet("pet number 1", entity),
                "pet number 2" , new SortedMapPet("pet number 2",entity)
        ));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(SortedMapOwner.class, entity.getId());
        Map<String, SortedMapPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSortedMap<String, SortedMapPet>),
                () -> assertEquals(originalCollection.keySet(), fetchedCollection.keySet()),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
