package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.array.ArrayOwner;
import com.reports.hibernate.model.entity.collection.array.ArrayPet;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.collection.array") // scan only required entities
@DisplayName("Array usage")
class ArrayCollectionTests extends BaseTest {

    @Test
    @DisplayName("Insert entity with array")
    void createAndGetEntity() {
        ArrayOwner entity = new ArrayOwner();
        ArrayPet[] originalCollection = Arrays.array(
                new ArrayPet("pet number 1", entity),
                new ArrayPet("pet number 2", entity));
        entity.setPets(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(ArrayOwner.class, entity.getId());
        ArrayPet[] fetchedCollection = entity.getPets();
        assertAll(
                () -> assertArrayEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertUpdateCount(originalCollection.length), // order columns values update
                () -> AssertQueryCount.assertInsertCount(originalCollection.length + 1)
        );
    }

}
