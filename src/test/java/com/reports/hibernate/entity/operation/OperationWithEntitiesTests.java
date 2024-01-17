package com.reports.hibernate.entity.operation;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.operation.OperationCat;
import com.reports.hibernate.model.entity.operation.OperationOwner;
import com.reports.hibernate.model.entity.operation.OperationDog;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EntityScan("com.reports.hibernate.model.entity.operation") // scan only required entities
@DisplayName("Operation wih entities")
class OperationWithEntitiesTests extends BaseTest {

    @Test
    @DisplayName("Save entity without flush")
    void saveOperationWithoutFlush() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        long generatedId = (long) session.save(entity);
        assertAll(
                () -> assertEquals(generatedId, entity.getId()),
                () -> AssertQueryCount.assertInsertCount(1)
        );
    }

    @Test
    @DisplayName("Save entity with flush")
    void saveOperationWithFlush() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        long generatedId = (long) session.save(entity);
        session.flush();
        assertAll(
                () -> assertEquals(generatedId, entity.getId()),
                () -> AssertQueryCount.assertInsertCount(1 + childrenAmount * 2)
        );
    }

    @Test
    @DisplayName("Persist entity without flush")
    void persistOperationWithoutFlush() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        session.persist(entity);
        assertAll(
                () -> assertNotEquals(0, entity.getId()),
                () -> AssertQueryCount.assertInsertCount(1 + childrenAmount * 2)
        );
    }

    @Test
    @DisplayName("Persist entity with flush")
    void persistOperationWithFlush() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        session.persist(entity);
        session.flush();
        assertAll(
                () -> assertNotEquals(0, entity.getId()),
                () -> AssertQueryCount.assertInsertCount(1 + childrenAmount * 2)
        );
    }

    @Test
    @DisplayName("Get entity")
    void getOperation() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        session.persist(entity);
        flushAndClear();
        AssertQueryCount.resetCount();
        OperationOwner retrievedEntity = session.get(OperationOwner.class, entity.getId());
        assertAll(
                () -> assertFalse(retrievedEntity instanceof HibernateProxy),
                () -> assertEquals(retrievedEntity.getCats().size(), childrenAmount),
                () -> assertEquals(retrievedEntity.getDogs().size(), childrenAmount),
                () -> AssertQueryCount.assertSelectCount(1)
        );
    }


    @Test
    @DisplayName("Load entity")
    void loadOperation() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        session.persist(entity);
        flushAndClear();
        AssertQueryCount.resetCount();
        OperationOwner retrievedEntity = session.load(OperationOwner.class, entity.getId());
        assertAll(
                () -> assertTrue(retrievedEntity instanceof HibernateProxy),
                () -> assertNotNull(retrievedEntity.getName()),
                () -> AssertQueryCount.assertSelectCount(1)
        );
    }

    @Test
    @DisplayName("Update entity")
    void updateOperation() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        session.persist(entity);
        flushAndClear();
        AssertQueryCount.resetCount();
        OperationOwner otherEntity = new OperationOwner();
        otherEntity.setId(entity.getId());
        otherEntity.setName("New name");
        session.update(otherEntity);
        session.flush();
        assertAll(
                () -> AssertQueryCount.assertUpdateCount(3)
                // 1 for filed update + 1 for each collection to break association
        );
    }


    @Test
    @DisplayName("Merge entity")
    void mergeOperation() {
        int childrenAmount = 3;
        OperationOwner entity = generateEntity(childrenAmount);
        session.persist(entity);
        flushAndClear();
        AssertQueryCount.resetCount();
        OperationOwner retrievedEntity = session.load(OperationOwner.class, entity.getId());
        assertAll(
                () -> assertTrue(retrievedEntity instanceof HibernateProxy),
                () -> assertNotNull(retrievedEntity.getName()),
                () -> AssertQueryCount.assertSelectCount(1)
        );
    }

    private OperationOwner generateEntity(int childrenAmount) {
        OperationOwner parentEntity = new OperationOwner();
        parentEntity.setName("Owner");
        parentEntity.setCats(
                IntStream.
                        range(0, childrenAmount).
                        mapToObj(index -> new OperationCat("Cat number " + index)).
                        collect(Collectors.toSet())
        );
        parentEntity.setDogs(
                IntStream.
                        range(0, childrenAmount).
                        mapToObj(index -> new OperationDog("Dog number " + index)).
                        collect(Collectors.toSet())
        );
        return parentEntity;
    }
}
