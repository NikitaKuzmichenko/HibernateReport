package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.map.MapOwner;
import com.reports.hibernate.model.entity.collection.map.MapPet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.collection.spi.PersistentMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.Map;

@EntityScan("com.reports.hibernate.model.entity.collection.map") // scan only required entities
@DisplayName("Map collection usage")
class MapCollectionTests extends BaseTest {

    @Test
    @DisplayName("Insert entity with map collection")
    void createAndGetEntity() {
        MapOwner entity = new MapOwner();
        Map<String, MapPet> originalCollection = Map.of(
                "key referencedEntity number 1", new MapPet("referencedEntity number 1", entity),
                "key referencedEntity number 2", new MapPet("referencedEntity number 2",entity));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(MapOwner.class, entity.getId());
        Map<String, MapPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentMap<String, MapPet>),
                () -> assertEquals(originalCollection.keySet(), fetchedCollection.keySet()),
                // values will be different bcs referenceEntity name was changed
                () -> AssertQueryCount.assertUpdateCount(originalCollection.size()),
                // key columns value update
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

    @Test
    @DisplayName("Insert duplicate child entity with duplicate key")
    void createAndGetEntityWithDuplication() {
        MapOwner entity = new MapOwner();
        Map<String, MapPet> originalCollection = Map.of(
                "key pet number 1", new MapPet("pet number 1",entity),
                "key pet number 2", new MapPet("pet number 2",entity));
        entity.setPets(originalCollection);

        MapPet duplicateKeyRef = new MapPet("key pet number 2",entity);
        session.persist(entity);
        session.persist(duplicateKeyRef);
        flushAndClear();
        
        List<MapPet> entitiesInTable = session
                .createQuery("SELECT e FROM MapPet e WHERE e.owner.id =:entityId", MapPet.class)
                .setParameter("entityId",entity.getId())
                .getResultList();

        entity = session.get(MapOwner.class, entity.getId());
        Map<String, MapPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertNotEquals(entitiesInTable.size(), fetchedCollection.size()),
                () -> assertTrue(fetchedCollection instanceof PersistentMap<String, MapPet>),
                () -> assertEquals(originalCollection.keySet(), fetchedCollection.keySet()),
                // values will be different bcs referenceEntity name was changed
                () -> AssertQueryCount.assertUpdateCount(originalCollection.size()),
                // key columns value update
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 2)
        );
    }

}
