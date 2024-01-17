package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.collection.DefaultOwner;
import com.reports.hibernate.model.entity.collection.collection.DefaultPet;
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
        DefaultOwner entity = new DefaultOwner();
        List<DefaultPet> originalCollection = List.of(
                new DefaultPet("pet number 1", entity),
                new DefaultPet("pet number 2", entity));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(DefaultOwner.class, entity.getId());
        Collection<DefaultPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentBag<DefaultPet>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
