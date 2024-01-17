package com.reports.hibernate.entity.state;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.state.AutoIdGenerationOwner;
import jakarta.persistence.PersistenceUnitUtil;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionImplementor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@EntityScan("com.reports.hibernate.model.entity.state") // scan only required entities
@DisplayName("Entity state changes")
class EntityStateTests extends BaseTest {

    private PersistenceUnitUtil unitUtil;

    @BeforeEach
    public void initUtils() {
        unitUtil = session.getSessionFactory().getPersistenceUnitUtil();
    }

    @Test
    @DisplayName("Entity with auto generated Id")
    void getStateManually() {
        AutoIdGenerationOwner owner = new AutoIdGenerationOwner(); // owner in transient state
        owner.setFirstName("First One");
        owner.setMiddleName("Middle One");
        owner.setLastName("Last One");

        boolean isUserInTransientStateBeforeInsert = isObjectInTransientState(owner);

        session.save(owner); // owner in managed state
        boolean isUserInManagedStateAfterInsert = isObjectInManagedState(owner);

        session.clear();// owner in detached state
        boolean isUserInDetachedStateAfterClear = isObjectInDetachedState(owner, owner.getId());

        session.update(owner); // owner in managed state
        boolean isUserInManagedStateAfterMerge = isObjectInManagedState(owner);

        session.remove(owner);
        boolean isUserInRemovedStateAfterRemove = isObjectInRemovedState(owner.getId(), AutoIdGenerationOwner.class);

        assertAll(
                () -> assertTrue(isUserInTransientStateBeforeInsert,
                        "Entity should be in Transient state after creation"), // transient state check
                () -> assertTrue(isUserInManagedStateAfterInsert,
                        "Entity should be in Manged state after insert"),// managed state check
                () -> assertTrue(isUserInManagedStateAfterMerge,
                        "Entity should be in Manged state after merge"), // managed state check
                () -> assertTrue(isUserInDetachedStateAfterClear,
                        "Entity should be in Detached state after session clear"), // detached state check
                () -> assertTrue(isUserInRemovedStateAfterRemove,
                        "Entity should be in Removed state after remove") // deleted state check
        );
    }

    private boolean isObjectInTransientState(Object object) {
        return unitUtil.getIdentifier(object) == null;
    }

    private boolean isObjectInManagedState(Object object) {
        return session.contains(object);
    }

    private boolean isObjectInDetachedState(Object object, Object objectId) {
        return objectId != null
               && objectId.equals(unitUtil.getIdentifier(object))
               && !(isObjectInManagedState(object));
    }

    private boolean isObjectInRemovedState(Object objectId, Class<?> cazz) {
        return session.get(cazz, objectId) == null;
    }


    public Map<Object, EntityEntry> getManagedEntities() {
        Map.Entry<Object, EntityEntry>[] entries = ((SessionImplementor) session)
                .getPersistenceContext()
                .reentrantSafeEntityEntries();
        return Arrays.stream(entries).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
