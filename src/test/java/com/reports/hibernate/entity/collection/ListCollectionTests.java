package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.list.ListOwner;
import com.reports.hibernate.model.entity.collection.list.ListPet;
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
        ListOwner entity = new ListOwner();
        List<ListPet> originalCollection = List.of(
                new ListPet("pet number 1", entity),
                new ListPet("pet number 2", entity));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(ListOwner.class, entity.getId());
        List<ListPet> fetchedCollection = entity.getPets();
        assertAll(
                () -> assertTrue(fetchedCollection instanceof PersistentBag<ListPet>),
                () -> assertEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertInsertCount(originalCollection.size() + 1)
        );
    }

}
