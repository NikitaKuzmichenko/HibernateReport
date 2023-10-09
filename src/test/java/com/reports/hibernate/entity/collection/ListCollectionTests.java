package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.list.ListCollectionEntity;
import com.reports.hibernate.model.entity.collection.list.ListReferencedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentBag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.collection.list") // scan only required entities
@DisplayName("List collection usage")
class ListCollectionTests extends BaseTest {

    @Test
    @DisplayName("Insert entity with list collection")
    void createAndGetEntity() {
        ListCollectionEntity entity = new ListCollectionEntity();
        List<ListReferencedEntity> originalCollection = List.of(
                new ListReferencedEntity("referencedEntity № 1", entity),
                new ListReferencedEntity("referencedEntity № 2", entity));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(ListCollectionEntity.class, entity.getId());
        List<ListReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentBag<ListReferencedEntity>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
