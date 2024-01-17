package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.sorted.custom.CustomComparatorSetOwner;
import com.reports.hibernate.model.entity.collection.sorted.custom.CustomComparatorSetPet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentSortedSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Set;
import java.util.TreeSet;

@EntityScan("com.reports.hibernate.model.entity.collection.sorted.custom") // scan only required entities
@DisplayName("Custom comparator set collection usage")
class CustomComparatorSetCollectionTests extends BaseTest {

    @Test
    @DisplayName("Insert entity with default set collection")
    void createEntityWithDefaultSetImpl() {
        CustomComparatorSetOwner entity = new CustomComparatorSetOwner();
        Set<CustomComparatorSetPet> originalCollection = Set.of(
                new CustomComparatorSetPet("pet number 1", entity),
                new CustomComparatorSetPet("pet number 2", entity));
        entity.setPets(originalCollection);
        assertThrows(java.lang.ClassCastException.class, () -> { // unable to cast Set to sortedSet
            session.persist(entity);
            flushAndClear();
        });
    }

    @Test
    @DisplayName("Insert entity with ordered set collection")
    void createAndGetEntityWithOrderedSet() {
        CustomComparatorSetOwner entity = new CustomComparatorSetOwner();
        Set<CustomComparatorSetPet> originalCollection = new TreeSet<>(Set.of(
                new CustomComparatorSetPet("pet number 1", entity),
                new CustomComparatorSetPet("pet number 2", entity)));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(CustomComparatorSetOwner.class, entity.getId());
        Set<CustomComparatorSetPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSortedSet<CustomComparatorSetPet>),
                () -> assertNotEquals(originalCollection.stream().toList(), fetchedCollection.stream().toList()),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
