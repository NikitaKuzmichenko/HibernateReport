package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.unsupported.UnsupportedCollectionEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.PriorityQueue;

@EntityScan("com.reports.hibernate.model.entity.collection.unsupported") // scan only required entities
@DisplayName("Default collection usage")
class UnsupportedCollectionTests extends BaseTest {

    @Test
    @DisplayName("Saving entity with unsupported collection type")
    void createAndGetEntity() {
        UnsupportedCollectionEntity entity = new UnsupportedCollectionEntity();
        entity.setReferencedEntities(new PriorityQueue<>());
        assertThrows(jakarta.persistence.PersistenceException.class, () -> {
            session.persist(entity);
            flushAndClear();
        });
    }

}
