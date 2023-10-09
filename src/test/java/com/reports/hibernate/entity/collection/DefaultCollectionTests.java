package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.collection.DefaultCollectionEntity;
import com.reports.hibernate.model.entity.collection.collection.DefaultReferencedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentBag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Collection;
import java.util.List;

@EntityScan("com.reports.hibernate.model.entity.collection.collection") // scan only required entities
@DisplayName("Collection usage")
class DefaultCollectionTests extends BaseTest {

    @Test
    @DisplayName("Insert entity with collection")
    void createAndGetEntity() {
        DefaultCollectionEntity entity = new DefaultCollectionEntity();
        List<DefaultReferencedEntity> originalCollection = List.of(
                new DefaultReferencedEntity("referencedEntity № 1", entity),
                new DefaultReferencedEntity("referencedEntity № 2", entity));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(DefaultCollectionEntity.class, entity.getId());
        Collection<DefaultReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentBag<DefaultReferencedEntity>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
