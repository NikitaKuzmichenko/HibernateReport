package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.sorted.set.SortedSetOwner;
import com.reports.hibernate.model.entity.collection.sorted.set.SortedSetPet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentSortedSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.*;

@EntityScan("com.reports.hibernate.model.entity.collection.sorted.set") // scan only required entities
@DisplayName("SortedSet collection usage")
class SortedSetCollectionTests extends BaseTest {
    @Test
    @DisplayName("Insert entity with sortedSet collection")
    void createAndGetEntity() {
        SortedSetOwner entity = new SortedSetOwner();
        SortedSet<SortedSetPet> originalCollection = new TreeSet<>(Set.of(
                new SortedSetPet("pet number 1", entity),
                new SortedSetPet("pet number 2",entity)));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(SortedSetOwner.class, entity.getId());
        Set<SortedSetPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSortedSet<SortedSetPet>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
