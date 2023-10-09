package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.set.SetCollectionEntity;
import com.reports.hibernate.model.entity.collection.set.SetReferencedEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.Set;

@EntityScan("com.reports.hibernate.model.entity.collection.set") // scan only required entities
@DisplayName("Set collection usage")
class SetCollectionTests extends BaseTest {
    @Test
    @DisplayName("Insert entity with set collection")
    void createAndGetEntity() {
        SetCollectionEntity entity = new SetCollectionEntity();
        Set<SetReferencedEntity> originalCollection = Set.of(
                new SetReferencedEntity("referencedEntity № 1", entity),
                new SetReferencedEntity("referencedEntity № 2",entity));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(SetCollectionEntity.class, entity.getId());
        Set<SetReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSet<SetReferencedEntity>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

    @Test
    @DisplayName("Insert duplicate child entity with duplicate key")
    void createAndGetEntityWithDuplication() {
        SetCollectionEntity entity = new SetCollectionEntity();
        Set<SetReferencedEntity> originalCollection = Set.of(
                new SetReferencedEntity("referencedEntity № 1", entity),
                new SetReferencedEntity("referencedEntity № 2",entity));
        entity.setReferencedEntities(originalCollection);

        SetReferencedEntity duplicateKeyRef = new SetReferencedEntity("referencedEntity № 2",entity);
        session.persist(entity);
        session.persist(duplicateKeyRef);
        flushAndClear();

        List<SetReferencedEntity> entitiesInTable = session
                .createQuery("SELECT e FROM SetReferencedEntity e WHERE e.collectionEntity.id =:entityId"
                        , SetReferencedEntity.class)
                .setParameter("entityId",entity.getId())
                .getResultList();

        entity = session.get(SetCollectionEntity.class, entity.getId());
        Set<SetReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertNotEquals(entitiesInTable.size(), fetchedCollection.size()),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> assertTrue(fetchedCollection instanceof PersistentSet<SetReferencedEntity>),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 2)
        );
    }
}
