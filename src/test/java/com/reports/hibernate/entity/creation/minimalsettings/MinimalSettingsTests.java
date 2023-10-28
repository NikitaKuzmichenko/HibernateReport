package com.reports.hibernate.entity.creation.minimalsettings;

import com.reports.hibernate.base.BaseTest;
import com.reports.hibernate.model.entity.creation.minimalsettings.MinimalSettingsEntity;
import com.reports.hibernate.sql.query.assertion.AssertQueryCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.reports.hibernate.model.entity.creation.minimalsettings") // scan only required entities
@DisplayName("Entity with minimal settings")
class MinimalSettingsTests extends BaseTest {

    @Test
    @DisplayName("Entity saving with all fields set")
    void createAndGetEntityWithAllFields() {
        MinimalSettingsEntity user = new MinimalSettingsEntity();
        user.setId(1L);
        user.setFirstName("First One");
        user.setMiddleName("Middle One");
        user.setLastName("Last One");
        session.save(user);
        flushAndClear();
        MinimalSettingsEntity fetchedUser = session.get(MinimalSettingsEntity.class, 1L);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser)
        );
    }

    @Test
    @DisplayName("Entity saving with only required fields set")
    void createAndGetEntityWithRequiredFields() {
        MinimalSettingsEntity user = new MinimalSettingsEntity();
        user.setId(1L);
        session.save(user);
        flushAndClear();
        MinimalSettingsEntity fetchedUser = session.get(MinimalSettingsEntity.class, 1L);
        assertAll(
                () -> AssertQueryCount.assertInsertCount(1),
                () -> AssertQueryCount.assertSelectCount(1),
                () -> assertEquals(user, fetchedUser),
                () -> assertNotSame(user, fetchedUser)
        );
    }

}
