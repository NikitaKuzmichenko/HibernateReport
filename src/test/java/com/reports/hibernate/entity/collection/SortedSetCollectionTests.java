package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.sorted.set.SortedSetCollectionEntity;
import com.reports.hibernate.model.entity.collection.sorted.set.SortedSetReferencedEntity;
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
        SortedSetCollectionEntity entity = new SortedSetCollectionEntity();
        SortedSet<SortedSetReferencedEntity> originalCollection = new TreeSet<>(Set.of(
                new SortedSetReferencedEntity("referencedEntity № 1", entity),
                new SortedSetReferencedEntity("referencedEntity № 2",entity)));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(SortedSetCollectionEntity.class, entity.getId());
        Set<SortedSetReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSortedSet<SortedSetReferencedEntity>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
