package com.reports.hibernate.entity.state;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.state.AutoIdGenerationEntity;
import com.reports.hibernate.model.entity.state.ManualIdGenerationEntity;
import jakarta.persistence.PersistenceUnitUtil;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.Status;
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
        AutoIdGenerationEntity user = new AutoIdGenerationEntity(); // user in transient state
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");

        boolean isUserInTransientStateBeforeInsert = isObjectInTransientState(user);

        session.save(user); // user in managed state
        boolean isUserInManagedStateAfterInsert = isObjectInManagedState(user);

        session.clear();// user in detached state
        boolean isUserInDetachedStateAfterClear = isObjectInDetachedState(user, user.getId());

        session.update(user); // user in managed state
        boolean isUserInManagedStateAfterMerge = isObjectInManagedState(user);

        session.remove(user);
        boolean isUserInRemovedStateAfterRemove = isObjectInRemovedState(user.getId(), AutoIdGenerationEntity.class);

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

//    @Test
//    @DisplayName("Entity with manually generated Id")
//    void getStateFromHibernate() {
//        ManualIdGenerationEntity user = new ManualIdGenerationEntity(); // user in transient state
//        user.setId(1L);
//        user.setFirstName("First One");
//        user.setMiddleName("Middle One");
//        user.setLastName("Last One");
//
//        boolean isUserInTransientStateBeforeInsert = isObjectInTransientState(user);
//
//        session.save(user); // user in managed state
//        boolean isUserInManagedStateAfterInsert = getManagedEntities().get(user) == null;
//
//        session.clear();// user in detached state
//        boolean isUserInDetachedStateAfterClear =  getManagedEntities().get(user).getStatus().equals(Status.GONE);
//
//        session.update(user); // user in managed state
//        boolean isUserInManagedStateAfterMerge = getManagedEntities().get(user).getStatus().equals(Status.MANAGED);
//
//        session.remove(user);
//        boolean isUserInRemovedStateAfterRemove = getManagedEntities().get(user).getStatus().equals(Status.DELETED);
//
//        assertAll(
//                () -> assertFalse(isUserInTransientStateBeforeInsert,
//                        "Entity should be in Transient state after creation, "
//                        + "but check failed because id was assigned manually"),// transient state check
//                () -> assertTrue(isUserInManagedStateAfterInsert,
//                        "Entity should be in Manged state after insert"),// managed state check
//                () -> assertTrue(isUserInManagedStateAfterMerge,
//                        "Entity should be in Manged state after merge"), // managed state check
//                () -> assertTrue(isUserInDetachedStateAfterClear,
//                        "Entity should be in Detached state after session clear"), // detached state check
//                () -> assertTrue(isUserInRemovedStateAfterRemove,
//                        "Entity should be in Removed state after remove") // deleted state check
//        );
//    }

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
