package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.map.MapCollectionEntity;
import com.reports.hibernate.model.entity.collection.map.MapReferencedEntity;
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
        MapCollectionEntity entity = new MapCollectionEntity();
        Map<String, MapReferencedEntity> originalCollection = Map.of(
                "key referencedEntity № 1", new MapReferencedEntity("referencedEntity № 1", entity),
                "key referencedEntity № 2", new MapReferencedEntity("referencedEntity № 2",entity));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(MapCollectionEntity.class, entity.getId());
        Map<String, MapReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentMap<String, MapReferencedEntity>),
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
        MapCollectionEntity entity = new MapCollectionEntity();
        Map<String, MapReferencedEntity> originalCollection = Map.of(
                "key referencedEntity № 1", new MapReferencedEntity("referencedEntity № 1",entity),
                "key referencedEntity № 2", new MapReferencedEntity("referencedEntity № 2",entity));
        entity.setReferencedEntities(originalCollection);

        MapReferencedEntity duplicateKeyRef = new MapReferencedEntity("key referencedEntity № 2",entity);
        session.persist(entity);
        session.persist(duplicateKeyRef);
        flushAndClear();
        
        List<MapReferencedEntity> entitiesInTable = session
                .createQuery("SELECT e FROM MapReferencedEntity e WHERE e.collectionEntity.id =:entityId"
                        , MapReferencedEntity.class)
                .setParameter("entityId",entity.getId())
                .getResultList();

        entity = session.get(MapCollectionEntity.class, entity.getId());
        Map<String, MapReferencedEntity> fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertNotEquals(entitiesInTable.size(), fetchedCollection.size()),
                () -> assertTrue(fetchedCollection instanceof PersistentMap<String, MapReferencedEntity>),
                () -> assertEquals(originalCollection.keySet(), fetchedCollection.keySet()),
                // values will be different bcs referenceEntity name was changed
                () -> AssertQueryCount.assertUpdateCount(originalCollection.size()),
                // key columns value update
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 2)
        );
    }

}
