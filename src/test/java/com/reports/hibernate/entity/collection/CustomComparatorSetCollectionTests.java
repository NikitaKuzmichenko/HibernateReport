package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.sorted.custom.CustomComparatorSetCollectionEntity;
import com.reports.hibernate.model.entity.collection.sorted.custom.CustomComparatorSetReferencedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentSet;
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
        CustomComparatorSetCollectionEntity entity = new CustomComparatorSetCollectionEntity();
        Set<CustomComparatorSetReferencedEntity> originalCollection = Set.of(
                new CustomComparatorSetReferencedEntity("referencedEntity № 1", entity),
                new CustomComparatorSetReferencedEntity("referencedEntity № 2", entity));
        entity.setReferencedEntities(originalCollection);
        assertThrows(java.lang.ClassCastException.class, () -> { // unable to cast Set to sortedSet
            session.persist(entity);
            flushAndClear();
        });
    }

    @Test
    @DisplayName("Insert entity with ordered set collection")
    void createAndGetEntityWithOrderedSet() {
        CustomComparatorSetCollectionEntity entity = new CustomComparatorSetCollectionEntity();
        Set<CustomComparatorSetReferencedEntity> originalCollection = new TreeSet<>(Set.of(
                new CustomComparatorSetReferencedEntity("referencedEntity № 1", entity),
                new CustomComparatorSetReferencedEntity("referencedEntity № 2", entity)));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(CustomComparatorSetCollectionEntity.class, entity.getId());
        Set<CustomComparatorSetReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSortedSet<CustomComparatorSetReferencedEntity>),
                () -> assertNotEquals(originalCollection.stream().toList(), fetchedCollection.stream().toList()),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
