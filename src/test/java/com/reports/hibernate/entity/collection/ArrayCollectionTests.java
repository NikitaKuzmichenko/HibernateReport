package com.reports.hibernate.entity.collection;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.collection.array.ArrayCollectionEntity;
import com.reports.hibernate.model.entity.collection.array.ArrayReferencedEntity;
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
        ArrayCollectionEntity entity = new ArrayCollectionEntity();
        ArrayReferencedEntity[] originalCollection = Arrays.array(
                new ArrayReferencedEntity("referencedEntity № 1", entity),
                new ArrayReferencedEntity("referencedEntity № 2", entity));
        entity.setReferencedEntities(originalCollection);
        session.persist(entity);
        flushAndClear();
        entity = session.get(ArrayCollectionEntity.class, entity.getId());
        ArrayReferencedEntity[] fetchedCollection = entity.getReferencedEntities();
        assertAll(
                () -> assertArrayEquals(originalCollection, fetchedCollection),
                () -> AssertQueryCount.assertUpdateCount(originalCollection.length), // order columns values update
                () -> AssertQueryCount.assertInsertCount(originalCollection.length + 1)
        );
    }

}
