package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.set.SetOwner;
import com.reports.hibernate.model.entity.collection.set.SetPet;
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
        SetOwner entity = new SetOwner();
        Set<SetPet> originalCollection = Set.of(
                new SetPet("referencedEntity number 1", entity),
                new SetPet("referencedEntity number 2",entity));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(SetOwner.class, entity.getId());
        Set<SetPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentSet<SetPet>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

    @Test
    @DisplayName("Insert duplicate child entity with duplicate key")
    void createAndGetEntityWithDuplication() {
        SetOwner entity = new SetOwner();
        Set<SetPet> originalCollection = Set.of(
                new SetPet("referencedEntity number 1", entity),
                new SetPet("referencedEntity number 2",entity));
        entity.setPets(originalCollection);

        SetPet duplicateKeyRef = new SetPet("referencedEntity number 2",entity);
        session.persist(entity);
        session.persist(duplicateKeyRef);
        flushAndClear();

        List<SetPet> entitiesInTable = session
                .createQuery("SELECT e FROM SetPet e WHERE e.owner.id =:entityId", SetPet.class)
                .setParameter("entityId",entity.getId())
                .getResultList();

        entity = session.get(SetOwner.class, entity.getId());
        Set<SetPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertNotEquals(entitiesInTable.size(), fetchedCollection.size()),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> assertTrue(fetchedCollection instanceof PersistentSet<SetPet>),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 2)
        );
    }
}
